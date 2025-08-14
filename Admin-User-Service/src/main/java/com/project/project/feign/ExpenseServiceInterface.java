package com.project.project.feign;

import com.project.project.models.Expense;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient("EXPENSE-SERVICE/admin")
public interface ExpenseServiceInterface {
    @GetMapping("/expenses")
    public ResponseEntity<Page<Expense>> getAllExpenses(
            @PageableDefault(sort = "dateTime", direction = Sort.Direction.DESC) Pageable pageable
    );

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id);

    @GetMapping("/stats/total-expenses")
    public ResponseEntity<Double> getTotalExpenses();

    @GetMapping("/stats/top-categories")
    public ResponseEntity<List<Object[]>> getTopCategories();

    @GetMapping("/stats/category-breakdown")
    public ResponseEntity<List<Object[]>> getCategoryBreakdown();

    @GetMapping("/stats/most-active-users")
    public ResponseEntity<List<Object[]>> getMostActiveUsers();

    @GetMapping("/stats/recent-submissions")
    public ResponseEntity<List<Expense>> getRecentSubmissions();
}
