package com.FinanceManagement.ExpensesManagement.service.Impl;

import com.FinanceManagement.ExpensesManagement.entities.Users;
import com.FinanceManagement.ExpensesManagement.exceptions.UserNotFoundException;
import com.FinanceManagement.ExpensesManagement.repositary.ExpenseTypesRepositary;
import com.FinanceManagement.ExpensesManagement.repositary.UserRepositary;
import com.FinanceManagement.ExpensesManagement.service.ExpenseTypesService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private JwtService jwtService;


    @Autowired
    private UserRepositary userRepositary;

    @Override
    public List<com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes> getAllExpenseTypes() {
        return expenseTypesRepositary.findAll();
    }

    @Override
    public com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes getExpenseType(String id) {
        return expenseTypesRepositary.findById(id).orElseThrow(() -> new RuntimeException("Expense Type Not Found"));
    }

    @Override
    public ResponseEntity<String> createExpenseType(String name , HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getUserNameFromToken(token);
        Users user = userRepositary.findByEmailId(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes expenseToCreate = new com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes();
        expenseToCreate.setType(name);
        expenseToCreate.setId(UUID.randomUUID().toString());
        expenseToCreate.setUser(user);
        expenseTypesRepositary.save(expenseToCreate);
        return new ResponseEntity<>("Expense Created" , HttpStatus.valueOf(200));




    }
}
