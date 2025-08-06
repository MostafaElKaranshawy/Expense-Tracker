package com.project.project.repositories;

import com.project.project.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

}
