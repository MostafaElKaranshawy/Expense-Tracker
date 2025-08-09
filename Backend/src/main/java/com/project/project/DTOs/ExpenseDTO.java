package com.project.project.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.project.project.enums.Category;
import com.project.project.models.Expense;
import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class ExpenseDTO {
    private Long id;
    private BigDecimal amount;
    private String currencyCode;
    private String description;
    private LocalDateTime dateTime;
    private String category;
    private int userId;
}
