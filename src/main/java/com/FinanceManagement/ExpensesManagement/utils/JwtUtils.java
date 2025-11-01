package com.FinanceManagement.ExpensesManagement.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final com.FinanceManagement.ExpensesManagement.service.Impl.JwtService jwtService;

    public JwtUtils(com.FinanceManagement.ExpensesManagement.service.Impl.JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public boolean isOwner(String emailId, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);
        String tokenUEmailId= jwtService.getUserNameFromToken(token);
        return emailId.equals(tokenUEmailId);
    }
}
