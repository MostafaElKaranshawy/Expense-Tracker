package com.project.project.services;

import com.project.project.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * All Services to be replaced with the rest template calls.
     */

    public Page<Expense> getAllExpenses(Pageable pageable) {
//        return expenseRepo.findAll(pageable);
        return null;
    }

    public Expense getExpenseById(Long id) {
//        return expenseRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return null;
    }

    public Map<String, Object> getSummaryStats() {
//        Map<String, Object> stats = new HashMap<>();
//        stats.put("totalExpenses", expenseRepo.getTotalExpenses());
//        stats.put("topCategories", expenseRepo.getTopCategories());
//        return stats;
        return null;
    }

    public List<Object[]> getCategoryBreakdown() {
//        return expenseRepo.getCategoryBreakdown();
        return null;
    }

    public Map<String, Object> getUserActivity() {
//        Map<String, Object> activity = new HashMap<>();
//        activity.put("mostActiveUsers", expenseRepo.getMostActiveUsers());
//        activity.put("recentSubmissions", expenseRepo.getRecentSubmissions());
//        return activity;
        return null;
    }
}
