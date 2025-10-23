package com.FinanceManagement.ExpensesManagement.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExpenseTypesService {
    List<com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes> getAllExpenseTypes();

    com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes getExpenseType(String id);

    ResponseEntity<String> createExpenseType(String name);

}
