package com.FinanceManagement.ExpensesManagement.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExpenseTypesService {
    List<com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes> getAllExpenseTypes();

    com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes getExpenseType(String id);

    ResponseEntity<String> createExpenseType(String name);

}
