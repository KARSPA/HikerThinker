package fr.karspa.hikerthinker.services;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Role;
import fr.karspa.hikerthinker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Dans user details service ..."+ username);

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not valid"));
    }
}
