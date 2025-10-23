package com.FinanceManagement.ExpensesManagement.service.Impl;

import com.FinanceManagement.ExpensesManagement.Entities.Users;
import com.FinanceManagement.ExpensesManagement.repositary.ExpenseTypesRepositary;
import com.FinanceManagement.ExpensesManagement.service.ExpenseTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseTypesServiceImpl implements ExpenseTypesService {

    @Autowired
    private ExpenseTypesRepositary expenseTypesRepositary;

    @Override
    public List<com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes> getAllExpenseTypes() {
        return expenseTypesRepositary.findAll();
    }

    @Override
    public com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes getExpenseType(String id) {
        return expenseTypesRepositary.findById(id).orElseThrow(() -> new RuntimeException("Excpense Type Not Found"));
    }

    @Override
    public ResponseEntity<String> createExpenseType(String name) {
        com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes expenseToCreate = new com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes();
        expenseToCreate.setType(name);
        expenseToCreate.setId(UUID.randomUUID().toString());

        Users user = new Users();
        user.setId("123");

        expenseToCreate.setUser(user);

        expenseTypesRepositary.save(expenseToCreate);

        return new ResponseEntity<>("Expense Created" , HttpStatus.valueOf(200));




    }
}
