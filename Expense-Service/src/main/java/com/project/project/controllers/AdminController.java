package com.project.project.controllers;

import com.project.project.exceptions.ExceptionsController;
import com.project.project.models.Expense;
import com.project.project.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ExceptionsController exceptionsController;

    @GetMapping("/expenses")
    public ResponseEntity<?> getAllExpenses(
            @PageableDefault(sort = "dateTime", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        try {
            return ResponseEntity.ok(
                    adminService.getAllExpenses(pageable)
            );
        }
        catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.getExpenseById(id));
        } catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stats/total-expenses")
    public ResponseEntity<?> getTotalExpenses() {
        try {
            return ResponseEntity.ok(adminService.getTotalExpenses());
        } catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/top-categories")
    public ResponseEntity<?> getTopCategories() {
        try {
            return ResponseEntity.ok(adminService.getTopCategories());
        } catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/category-breakdown")
    public ResponseEntity<?> getCategoryBreakdown() {
        try {
            return ResponseEntity.ok(adminService.getCategoryBreakdown());
        } catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/most-active-users")
    public ResponseEntity<?> getMostActiveUsers() {
        try {
            return ResponseEntity.ok(adminService.getMostActiveUsers());
        }
        catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/recent-submissions")
    public ResponseEntity<?> getRecentSubmissions() {
        try {
            return ResponseEntity.ok(adminService.getRecentSubmissions());
        }
        catch (Exception e) {
            return exceptionsController.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
