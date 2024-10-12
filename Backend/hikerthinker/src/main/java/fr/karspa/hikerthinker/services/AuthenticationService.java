package fr.karspa.hikerthinker.services;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Role;
import fr.karspa.hikerthinker.dto.LoginResponseDTO;
import fr.karspa.hikerthinker.dto.RegistrationDTO;
import fr.karspa.hikerthinker.dto.RegistrationResponseDTO;
import fr.karspa.hikerthinker.repository.RoleRepository;
import fr.karspa.hikerthinker.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.HashSet;

@Service
@Transactional()
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public RegistrationResponseDTO registerUser(String username, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        try{
            ApplicationUser newUser = userRepository.saveAndFlush(new ApplicationUser(username, email, encodedPassword, authorities));
            return new RegistrationResponseDTO(newUser.getUsername(),"Utilisateur créé avec succès !",200, true);
        } catch(Exception err ) { //Erreur non catchée pour une raison obscure, on peut faire avec ...
            return new RegistrationResponseDTO("","L'utilisateur n'a pas pu être créé !", 500, false);
        }
    }


    public LoginResponseDTO loginUser(String username, String password) {

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJwt(authentication);
            ApplicationUser foundUser = userRepository.findByUsername(username).get();

            return new LoginResponseDTO(foundUser.getUserId(), foundUser.getUsername(), foundUser.getEmail(), token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}
