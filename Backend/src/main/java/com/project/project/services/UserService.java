package com.project.project.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.project.models.User;
import com.project.project.repositories.UserRepo;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepository;

    public User getUserById(HttpServletRequest request) {
        User user = jwtService.getAuthenticatedUser(request);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User updateUser(User userDetails, HttpServletRequest request) {
        User currentUser = jwtService.getAuthenticatedUser(request);
        int id = currentUser.getId();
        if (currentUser == null || !(currentUser.getId() == id)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized to update this user");
        }

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        existingUser.setUsername(userDetails.getUsername());
        return userRepository.save(existingUser);
    }

    public boolean deleteUser(HttpServletRequest request) {
        User currentUser = jwtService.getAuthenticatedUser(request);
        int id = currentUser.getId();
        if (currentUser == null || !(currentUser.getId() == id)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized to delete this user");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userRepository.delete(user);
        return true;
    }

}
