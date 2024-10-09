package fr.karspa.hikerthinker.controller;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.dto.LoginResponseDTO;
import fr.karspa.hikerthinker.dto.RegistrationDTO;
import fr.karspa.hikerthinker.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        return  authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        //On utilise le registrationDTO en ne prennant que l'username et passsword, on pourrait créer un LoginDTO également...
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
