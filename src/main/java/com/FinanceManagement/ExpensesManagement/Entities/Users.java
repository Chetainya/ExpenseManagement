package com.FinanceManagement.ExpensesManagement.Entities;


import com.FinanceManagement.ExpensesManagement.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Users {

    @Id
    String id;
    String firstName;
    String lastName;
    String emailId;
    String contactNumber;
    Role role;


}
