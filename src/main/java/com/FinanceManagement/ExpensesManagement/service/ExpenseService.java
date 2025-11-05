package com.FinanceManagement.ExpensesManagement.service;

import com.FinanceManagement.ExpensesManagement.dto.ExpenseRequestDto;
import com.FinanceManagement.ExpensesManagement.entities.Expenses;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ExpenseService {

    Expenses createExpense(ExpenseRequestDto expenseRequestDto , HttpServletRequest request);

    Expenses editExpense(ExpenseRequestDto expenseRequestDto , String id , HttpServletRequest request);

    List<Expenses> getAllExpense(HttpServletRequest request);


}
