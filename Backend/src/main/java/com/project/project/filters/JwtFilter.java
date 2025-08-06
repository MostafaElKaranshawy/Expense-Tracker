package com.project.project.filters;

import com.project.project.Models.User;
import com.project.project.repositories.UserRepo;
import com.project.project.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userName = jwtService.extractUserNameFromToken(token);
            System.out.println("Extracted token: " + token);
            System.out.println("Extracted username from token: " + userName);
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService().loadUserByUsername(userName);
            if (jwtService.isTokenValid(token, userDetails)) {
                // Create an authentication object and set it in the security context
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Token is valid for user: " + userName);
                request.getSession().setAttribute("user", userDetails);
            }
            System.out.println("Processing request for user: " + userName);
        }
        System.out.println("JWT Filter is processing the request for: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = repository.findByUsername(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER") // adjust role if needed
                    .build();
        };
    }
}
