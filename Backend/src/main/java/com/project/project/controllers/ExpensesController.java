package com.project.project.controllers;

import com.project.project.DTOs.ExpenseDTO;
import com.project.project.adapters.ExpensesAndDTOAdapter;
import com.project.project.enums.Category;
import com.project.project.models.Expense;
import com.project.project.models.User;
import com.project.project.repositories.UserRepo;
import com.project.project.services.ExpensesService;
import com.project.project.services.JwtService;
import com.project.project.services.LocalizationService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ExpensesAndDTOAdapter expensesAdapter;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody Expense expense, HttpServletRequest request, Locale locale) {
        User currentUser = jwtService.getAuthenticatedUser(request);
        expense.setUser(currentUser);
        Expense created = expensesService.createExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(expensesAdapter.toDTO(created));
    }

    @GetMapping
    public List<Expense> getUserExpenses(HttpServletRequest request) {
        User currentUser = jwtService.getAuthenticatedUser(request);
        return expensesService.getUserExpenses((long) currentUser.getId());
    }

    @PutMapping("/{expenseId}")
    public Expense updateExpense(@PathVariable Long expenseId, @RequestBody Expense updatedExpense, HttpServletRequest request) {
        User currentUser = jwtService.getAuthenticatedUser(request);
        return expensesService.updateExpense((long)currentUser.getId(), expenseId, updatedExpense);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId, HttpServletRequest request) {
        User currentUser = jwtService.getAuthenticatedUser(request);

        expensesService.deleteExpense((long) currentUser.getId(), expenseId);
    }

    @GetMapping("/filter")
    public List<Expense> filterExpenses(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            HttpServletRequest request) {
        User currentUser = jwtService.getAuthenticatedUser(request);
        return expensesService.filterExpenses((long) currentUser.getId(), category, startDate, endDate);
    }
}