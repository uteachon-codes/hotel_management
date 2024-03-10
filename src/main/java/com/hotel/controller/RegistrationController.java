package com.hotel.controller;

import com.hotel.model.User;
import com.hotel.model.UserModel;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody UserModel userModel){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userModel.getEmail(), userModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel) {

        User user = userService.registerUser(userModel);
        return "Success";
    }

}
