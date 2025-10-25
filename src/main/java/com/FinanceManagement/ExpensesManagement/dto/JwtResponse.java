package com.FinanceManagement.ExpensesManagement.dto;

import com.FinanceManagement.ExpensesManagement.entities.Users;

public record JwtResponse (

        String accessToken,
        String refreshToken,
        Users user
){



}

