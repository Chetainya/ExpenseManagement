package com.FinanceManagement.ExpensesManagement.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Expenses {
    @Id
    String id;
    int amount;

    @ManyToOne
    @JoinColumn(name = "expense_type_id")
    ExpenseTypes expenseType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}
