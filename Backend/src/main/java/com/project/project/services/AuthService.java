package com.project.project.services;

import com.project.project.DTOs.UserDTO;
import com.project.project.enums.Role;
import com.project.project.models.User;
import com.project.project.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("User already exists");
        }
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    // return token
    public UserDTO login(String username, String password) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            if(user == null)
                throw new IllegalArgumentException("User not found");
            String token = JwtService.generateToken(user);
            System.out.println("Generated Token: " + token);
            UserDTO userDTO = new UserDTO();
            userDTO.setToken(token);
            userDTO.setUser(user);
            return userDTO;
        }
        else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }
}
