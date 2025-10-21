package com.FinanceManagement.ExpensesManagement.service;

import com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExpenseType {
    List<ExpenseTypes> getAllExpenseTypes();

    ExpenseTypes getExpenseType(String id);

    ResponseEntity<String> createExpenseType(String name);

}
