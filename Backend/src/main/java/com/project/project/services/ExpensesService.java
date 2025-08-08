package com.project.project.services;

import com.project.project.enums.Category;
import com.project.project.models.Expense;
import com.project.project.repositories.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpensesService {

    @Autowired
    private ExpenseRepo expenseRepository;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Expense updateExpense(Long userId, Long expenseId, Expense updatedExpense) {
        return expenseRepository.findById(expenseId).map(expense -> {
            if (expense.getUser().getId() != userId) {
                throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Not authorized to update this expense"
                );
            }
            expense.setAmount(updatedExpense.getAmount());
            expense.setCurrencyCode(updatedExpense.getCurrencyCode());
            expense.setDescription(updatedExpense.getDescription());
            expense.setDateTime(updatedExpense.getDateTime());
            expense.setCategory(updatedExpense.getCategory());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public void deleteExpense(Long userId, Long expenseId) {
        expenseRepository.findById(expenseId).ifPresentOrElse(expense -> {
            if (expense.getUser().getId() != userId) {
                throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Not authorized to delete this expense"
                );
            }
            expenseRepository.deleteById(expenseId);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found");
        });
    }

    public List<Expense> filterExpenses(Long userId, Category category, LocalDate startDate, LocalDate endDate) {
        boolean hasCategory = category != null;
        boolean hasDateRange = startDate != null && endDate != null;

        if (hasCategory && hasDateRange) {
            return expenseRepository.findByUserIdAndCategoryAndDateTimeBetween(
                userId, category,
                startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)
            );
        } else if (hasCategory) {
            return expenseRepository.findByUserIdAndCategory(userId, category);
        } else if (hasDateRange) {
            return expenseRepository.findByUserIdAndDateTimeBetween(
                userId,
                startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)
            );
        } else {
            return expenseRepository.findByUserId(userId);
        }
    }

}