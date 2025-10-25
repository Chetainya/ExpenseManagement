package com.FinanceManagement.ExpensesManagement.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExpenseTypes {
    @Id
    String id;
    String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;

}
