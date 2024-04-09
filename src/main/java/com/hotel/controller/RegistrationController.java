package com.hotel.controller;

import com.hotel.model.AuthResponse;
import com.hotel.model.User;
import com.hotel.model.UserModel;
import com.hotel.security.CustomUserDetailsService;
import com.hotel.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Autowired
    private UserService userService;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@RequestBody UserModel loginRequest) {
        Optional<User> userOptional = userService.validEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getEmail(), user.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')" )
    public String registerUser(@RequestBody UserModel userModel) {

        User user = userService.registerUser(userModel);
        return "Success";
    }
}
