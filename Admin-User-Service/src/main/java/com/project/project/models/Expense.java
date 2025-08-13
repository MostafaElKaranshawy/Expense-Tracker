package com.project.project.models;

import com.project.project.enums.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense {
    private Long id;
    private BigDecimal amount;
    private String currencyCode;
    private String description;
    private LocalDateTime dateTime;
    private Category category;
    private int userId;

    public Expense() {
    }

    public Expense(Long id, BigDecimal amount, String currencyCode, String description, LocalDateTime dateTime, Category category, int userId) {
        this.id = id;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.description = description;
        this.dateTime = dateTime;
        this.category = category;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
