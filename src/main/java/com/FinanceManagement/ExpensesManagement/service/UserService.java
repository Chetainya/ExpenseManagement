package com.FinanceManagement.ExpensesManagement.service;

import com.FinanceManagement.ExpensesManagement.dto.UserRequestDTO;
import com.FinanceManagement.ExpensesManagement.entities.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> register(UserRequestDTO userRequest) throws Exception;


    ResponseEntity<Users> getUserById(String id , HttpServletRequest request);
}
