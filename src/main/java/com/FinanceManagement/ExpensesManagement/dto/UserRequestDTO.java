package com.FinanceManagement.ExpensesManagement.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String contactNumber;
}
