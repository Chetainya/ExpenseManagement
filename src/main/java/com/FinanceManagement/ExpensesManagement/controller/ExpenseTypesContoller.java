package com.FinanceManagement.ExpensesManagement.controller;


import com.FinanceManagement.ExpensesManagement.service.ExpenseTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenseType")
public class ExpenseTypesContoller {


    private ExpenseTypesService expenseTypesService;

    public ExpenseTypesContoller(ExpenseTypesService expenseTypesService){
        this.expenseTypesService = expenseTypesService;
    }


    @GetMapping("/")
    public List<com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes> getAllExpenses(){
        return expenseTypesService.getAllExpenseTypes();
    }

    @GetMapping("/{id}")
    public com.FinanceManagement.ExpensesManagement.Entities.ExpenseTypes getExpenseType(@PathVariable String id){
        return expenseTypesService.getExpenseType(id);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> createExpenseType(@PathVariable String name){
        return expenseTypesService.createExpenseType(name);
    }


}
