package com.FinanceManagement.ExpensesManagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @JoinColumn(name = "expense_type_id" , nullable = false)
    ExpenseTypes expenseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonIgnore
    Users user;
}
