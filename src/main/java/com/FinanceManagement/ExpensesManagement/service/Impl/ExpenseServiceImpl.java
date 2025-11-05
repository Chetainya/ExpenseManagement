package com.FinanceManagement.ExpensesManagement.service.Impl;

import com.FinanceManagement.ExpensesManagement.dto.ExpenseRequestDto;
import com.FinanceManagement.ExpensesManagement.entities.ExpenseTypes;
import com.FinanceManagement.ExpensesManagement.entities.Expenses;
import com.FinanceManagement.ExpensesManagement.entities.Users;
import com.FinanceManagement.ExpensesManagement.exceptions.UserNotFoundException;
import com.FinanceManagement.ExpensesManagement.repositary.ExpenseRepositary;
import com.FinanceManagement.ExpensesManagement.repositary.ExpenseTypesRepositary;
import com.FinanceManagement.ExpensesManagement.repositary.UserRepositary;
import com.FinanceManagement.ExpensesManagement.service.ExpenseService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ExpenseServiceImpl implements ExpenseService {


    @Autowired
    private JwtService jwtService;



    @Autowired
    private ExpenseTypesRepositary expenseTypesRepositary;


    @Autowired
    private UserRepositary userRepositary;


    @Autowired
    private ExpenseRepositary expenseRepositary;

    @Override
    public Expenses createExpense(ExpenseRequestDto expenseRequestDto, HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String userName = jwtService.getUserNameFromToken(token);

        ExpenseTypes expenseType = expenseTypesRepositary.findById(expenseRequestDto.getExpenseTypeId()).orElseThrow(() -> new RuntimeException("Invaild Expense Type"));


        Expenses expense = new Expenses();
        expense.setExpenseType(expenseType);
        expense.setId(UUID.randomUUID().toString());
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setUser(userRepositary.findByEmailId(userName).orElseThrow(() -> new UserNotFoundException("User Not Found")));

        return expenseRepositary.save(expense);



    }

    @Override
    public Expenses editExpense(ExpenseRequestDto expenseRequestDto, String id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userName = jwtService.getUserNameFromToken(token);

        Users user = userRepositary.findByEmailId(userName).orElseThrow(() -> new UserNotFoundException("User not Found"));

        Expenses expenses = expenseRepositary.findById(id).orElseThrow(() -> new RuntimeException("Expense Not Found"));

        if(!user.getEmailId().equals(expenses.getUser().getEmailId())){
            throw new JwtException("Unauthorised Access to the resource");
        }

        ExpenseTypes expenseType = expenseTypesRepositary.findById(expenseRequestDto.getExpenseTypeId()).orElseThrow(() -> new RuntimeException("Invaild Expense Type"));


        expenses.setExpenseType(expenseType);
        expenses.setAmount(expenseRequestDto.getAmount());

        return expenseRepositary.save(expenses);


    }

    @Override
    public List<Expenses> getAllExpense(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userName = jwtService.getUserNameFromToken(token);

        return expenseRepositary.getAllExpensesByEmailId(userName);


    }
}
