package com.project.project.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.project.project.DTOs.ExpenseDTO;
import com.project.project.models.Expense;
import com.project.project.services.LocalizationService;

@Component
public class ExpensesAndDTOAdapter {
    @Autowired
    private LocalizationService localizationService;

    public ExpenseDTO toDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setDateTime(expense.getDateTime());
        expenseDTO.setCurrencyCode(expense.getCurrencyCode());
        expenseDTO.setUserId(expense.getUser().getId());
        expenseDTO.setCategory(localizationService.getCategoryName(expense.getCategory(), LocaleContextHolder.getLocale()));
        return expenseDTO;
    }
}
