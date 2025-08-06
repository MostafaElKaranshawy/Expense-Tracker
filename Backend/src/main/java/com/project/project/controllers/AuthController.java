package com.project.project.controllers;

import com.project.project.DTOs.UserDTO;
import com.project.project.Exceptions.ExceptionsController;
import com.project.project.Models.User;
import com.project.project.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ExceptionsController exceptionsController;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = authService.register(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                return exceptionsController.handleIllegalArgument((IllegalArgumentException) e,
                        "Registration failed", HttpStatus.BAD_REQUEST);
            }
            return exceptionsController.handleException(e, "Registration failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            UserDTO userDTO = authService.login(user.getUsername(), user.getPassword());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            if( e instanceof IllegalArgumentException) {
                return exceptionsController.handleIllegalArgument((IllegalArgumentException) e,
                        "Login failed", HttpStatus.UNAUTHORIZED);
            }
            return exceptionsController.handleException(e, "Login failed", HttpStatus.BAD_REQUEST);
        }
    }
}
