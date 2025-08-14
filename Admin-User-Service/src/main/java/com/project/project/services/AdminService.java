package com.project.project.services;

import com.project.project.feign.ExpenseServiceInterface;
import com.project.project.models.Expense;
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
    private ExpenseServiceInterface expenseService;

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseService.getAllExpenses(pageable).getBody();
    }

    public Expense getExpenseById(Long id) {
        return expenseService.getExpenseById(id).getBody();
    }


    public Map<String, Object> getSummaryStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalExpenses", expenseService.getTotalExpenses().getBody());
        stats.put("topCategories", expenseService.getTopCategories().getBody());
        return stats;
    }

    public List<Object[]> getCategoryBreakdown() {
        return expenseService.getCategoryBreakdown().getBody();
    }

    public Map<String, Object> getUserActivity() {
        Map<String, Object> activity = new HashMap<>();
        activity.put("mostActiveUsers", expenseService.getMostActiveUsers().getBody());
        activity.put("recentSubmissions", expenseService.getRecentSubmissions().getBody());
        return activity;
    }
}
