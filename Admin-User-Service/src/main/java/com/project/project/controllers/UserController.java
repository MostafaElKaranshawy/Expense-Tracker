package com.project.project.controllers;

import com.project.project.exceptions.ExceptionsController;
import com.project.project.models.User;
import com.project.project.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExceptionsController exceptionsController;

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(HttpServletRequest request) {
        try {
            User user = userService.getUserById(request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User userDetails,
                                           HttpServletRequest request) {
        try {
            User updatedUser = userService.updateUser(userDetails, request);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        try {
            boolean isDeleted = userService.deleteUser(request);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.BAD_REQUEST);
        }
    }

}



























































