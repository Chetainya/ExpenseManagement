package com.FinanceManagement.ExpensesManagement.entities;


import com.FinanceManagement.ExpensesManagement.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Users {

    @Id
    String id;
    String firstName;
    String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @Column(unique = true, nullable = false)
    String emailId;
    String contactNumber;

    @Enumerated(EnumType.STRING)
    Role role;


    @OneToMany(mappedBy = "user")
    List<ExpenseTypes> expenseTypes = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    List<Expenses> expenses = new ArrayList<>();

}
