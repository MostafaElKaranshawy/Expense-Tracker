package com.project.project.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.project.project.enums.Category;
import com.project.project.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

    List<Expense> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Expense> findByUserIdAndCategoryAndDateTimeBetween(Long userId, Category category, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Expense> findByUserIdAndCategory(Long userId, Category category);
}
