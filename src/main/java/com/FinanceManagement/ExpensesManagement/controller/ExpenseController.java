package com.FinanceManagement.ExpensesManagement.controller;


import com.FinanceManagement.ExpensesManagement.dto.ExpenseRequestDto;
import com.FinanceManagement.ExpensesManagement.dto.RequestDTO;
import com.FinanceManagement.ExpensesManagement.entities.Expenses;
import com.FinanceManagement.ExpensesManagement.service.ExpenseService;
import com.FinanceManagement.ExpensesManagement.service.Impl.ExpenseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {


    @Autowired
    private ExpenseServiceImpl expenseService;


    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping()
    public Expenses createExpense(@RequestBody RequestDTO requestDTO , HttpServletRequest request){

        ExpenseRequestDto expenseRequestDto = objectMapper.convertValue(requestDTO.getApiData(), ExpenseRequestDto.class);


        return expenseService.createExpense(expenseRequestDto , request);
    }

    @PutMapping("/{id}")
    public Expenses editExpense(@RequestBody RequestDTO requestDTO , @PathVariable String id , HttpServletRequest request){

        ExpenseRequestDto expenseRequestDto = objectMapper.convertValue(requestDTO.getApiData(), ExpenseRequestDto.class);

        return expenseService.editExpense(expenseRequestDto , id , request);

    }

    @GetMapping()
    public List<Expenses> getAllExpense(HttpServletRequest request){
        return expenseService.getAllExpense(request);
    }




}
