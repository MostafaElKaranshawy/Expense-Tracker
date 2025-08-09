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

    public Map<String, Object> getSummaryStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalExpenses", expenseRepo.getTotalExpenses());
        stats.put("topCategories", expenseRepo.getTopCategories());
        return stats;
    }

    public List<Object[]> getCategoryBreakdown() {
        return expenseRepo.getCategoryBreakdown();
    }

    public Map<String, Object> getUserActivity() {
        Map<String, Object> activity = new HashMap<>();
        activity.put("mostActiveUsers", expenseRepo.getMostActiveUsers());
        activity.put("recentSubmissions", expenseRepo.getRecentSubmissions());
        return activity;
    }
}
