package com.easyschool.backend.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        
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

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MODERATOR')")
     @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
        }
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
          
        try {
            userService.deleteUserById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

}   
