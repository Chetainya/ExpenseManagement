package com.FinanceManagement.ExpensesManagement.controller;


import com.FinanceManagement.ExpensesManagement.service.ExpenseTypesService;
import jakarta.servlet.http.HttpServletRequest;
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
    public List<com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes> getAllExpenses(){
        return expenseTypesService.getAllExpenseTypes();
    }

    @GetMapping("/{id}")
    public com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes getExpenseType(@PathVariable String id){
        return expenseTypesService.getExpenseType(id);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> createExpenseType(@PathVariable String name , HttpServletRequest request){
        return expenseTypesService.createExpenseType(name , request);
    }


}
