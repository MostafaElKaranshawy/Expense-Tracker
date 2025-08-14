package com.project.project.services;

import com.project.project.models.Expense;
import com.project.project.repositories.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private ExpenseRepo expenseRepo;

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepo.findAll(pageable);
    }

    public Expense getExpenseById(Long id) {
        return expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public Double getTotalExpenses() {
        return expenseRepo.getTotalExpenses();
    }

    public List<Object[]> getTopCategories() {
        return expenseRepo.getTopCategories();
    }

    public List<Object[]> getCategoryBreakdown() {
        return expenseRepo.getCategoryBreakdown();
    }

    public List<Object[]> getMostActiveUsers() {
        return expenseRepo.getMostActiveUsers();
    }

    public List<Expense> getRecentSubmissions() {
        return expenseRepo.getRecentSubmissions();
    }
}
