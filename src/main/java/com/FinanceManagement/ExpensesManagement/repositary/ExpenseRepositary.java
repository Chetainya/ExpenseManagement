package com.FinanceManagement.ExpensesManagement.repositary;

import com.FinanceManagement.ExpensesManagement.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;


@org.springframework.stereotype.Repository
public interface ExpenseRepositary extends JpaRepository<Expenses, String> {


    @Query("Select p from Expenses p where p.user.emailId = :emailId")
    List<Expenses> getAllExpensesByEmailId(String emailId);

}