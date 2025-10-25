package com.FinanceManagement.ExpensesManagement.entities;


import com.FinanceManagement.ExpensesManagement.enums.Role;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

    @Id
    String id;
    String firstName;
    String lastName;
    String password;

    @Column(unique = true, nullable = false)
    String emailId;
    String contactNumber;

    @Enumerated(EnumType.STRING)
    Role role;


}
