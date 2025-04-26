package com.easyschool.backend.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyschool.backend.model.Credential;
import com.easyschool.backend.model.User;
import com.easyschool.backend.payload.request.LoginRequest;
import com.easyschool.backend.payload.request.SignupRequest;
import com.easyschool.backend.payload.response.JwtResponse;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.CredentialService;
import com.easyschool.backend.service.RoleService;
import com.easyschool.backend.service.UserService;
import com.easyschool.backend.util.security.jwt.JwtUtils;
import com.easyschool.backend.util.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  RoleService roleService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Gestisce il login degli utenti.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Ottiene i dettagli dell'utente autenticato
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Gestisce la registrazione di nuovi utenti.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        // Verifica se l'email è già registrata
        if (credentialService.existByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
    
           Credential credential = new Credential();
            credential.setEmail(signUpRequest.getEmail());
            credential.setPassword(encoder.encode(signUpRequest.getPassword()));
            credentialService.saveCredential(credential);

            User user = new User();
            user.setName(signUpRequest.getName());
            user.setSurname(signUpRequest.getSurname());
            user.setBirthdate(signUpRequest.getBirthdate());
            user.setCredentials(credential); 
            user.setRole(roleService.getRoleByCode(signUpRequest.getRole_code()));
            userService.saveUser(user);
            credential.setUser(user); 
            

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
