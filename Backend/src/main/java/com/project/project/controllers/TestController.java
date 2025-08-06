package com.project.project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<Integer> test(HttpServletRequest request) {
        int userId = (Integer) request.getSession().getAttribute("userId");
        return new ResponseEntity<>(userId,HttpStatus.OK);
    }
}
