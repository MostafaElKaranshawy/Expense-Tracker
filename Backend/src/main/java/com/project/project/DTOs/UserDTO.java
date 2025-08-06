package com.project.project.DTOs;

import com.project.project.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTO {
    User user;
    String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
