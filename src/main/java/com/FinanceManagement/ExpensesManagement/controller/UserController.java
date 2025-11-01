package com.FinanceManagement.ExpensesManagement.controller;


import com.FinanceManagement.ExpensesManagement.dto.RequestDTO;
import com.FinanceManagement.ExpensesManagement.dto.UserRequestDTO;
import com.FinanceManagement.ExpensesManagement.entities.Users;
import com.FinanceManagement.ExpensesManagement.exceptions.UserNotFoundException;
import com.FinanceManagement.ExpensesManagement.service.Impl.CustomUserDetail;
import com.FinanceManagement.ExpensesManagement.service.UserService;
import com.FinanceManagement.ExpensesManagement.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RequestDTO userData) throws Exception{
        UserRequestDTO userRequestDTO = objectMapper.convertValue(userData.getApiData(), UserRequestDTO.class);

        return userService.register(userRequestDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> registerUser(@PathVariable String id , HttpServletRequest request) {
        return userService.getUserById(id , request);

    }



}
