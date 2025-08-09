package com.project.project.controllers;

import com.project.project.models.Expense;
import com.project.project.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/expenses")
    public ResponseEntity<Page<Expense>> getAllExpenses(
            @PageableDefault(sort = "dateTime", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                adminService.getAllExpenses(pageable)
        );
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getExpenseById(id));
    }

    @GetMapping("/stats/summary")
    public ResponseEntity<Map<String, Object>> getSummaryStats() {
        return ResponseEntity.ok(adminService.getSummaryStats());
    }

    @GetMapping("/stats/category-breakdown")
    public ResponseEntity<List<Object[]>> getCategoryBreakdown() {
        return ResponseEntity.ok(adminService.getCategoryBreakdown());
    }

    @GetMapping("/stats/user-activity")
    public ResponseEntity<Map<String, Object>> getUserActivity() {
        return ResponseEntity.ok(adminService.getUserActivity());
    }
}
