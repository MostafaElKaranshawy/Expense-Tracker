package com.project.project.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.project.project.enums.Category;
import com.project.project.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

    List<Expense> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Expense> findByUserIdAndCategoryAndDateTimeBetween(Long userId, Category category, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Expense> findByUserIdAndCategory(Long userId, Category category);

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpenses();

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category ORDER BY SUM(e.amount) DESC")
    List<Object[]> getTopCategories();

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
    List<Object[]> getCategoryBreakdown();

    @Query("SELECT e.user.username, COUNT(e) FROM Expense e GROUP BY e.user.username ORDER BY COUNT(e) DESC")
    List<Object[]> getMostActiveUsers();

    @Query("SELECT e FROM Expense e ORDER BY e.dateTime DESC LIMIT 10")
    List<Expense> getRecentSubmissions();
}
