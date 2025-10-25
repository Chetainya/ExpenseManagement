package com.FinanceManagement.ExpensesManagement.service.Impl;

import com.FinanceManagement.ExpensesManagement.dto.UserRequestDTO;
import com.FinanceManagement.ExpensesManagement.entities.Users;
import com.FinanceManagement.ExpensesManagement.enums.Role;
import com.FinanceManagement.ExpensesManagement.exceptions.UserNotFoundException;
import com.FinanceManagement.ExpensesManagement.repositary.UserRepositary;
import com.FinanceManagement.ExpensesManagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositary userRepositary;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public ResponseEntity<String> register(UserRequestDTO userRequest) throws Exception {
        Users user = objectMapper.convertValue(userRequest, Users.class);
        user.setId(UUID.randomUUID().toString());
        user.setRole(Role.valueOf("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositary.save(user);
        return new ResponseEntity<>(user.getId() ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Users> getUserById(String id) {

        Optional<Users> byId = userRepositary.findById(id);
        if(byId.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        CustomUserDetail principal = (CustomUserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String username = principal.getUsername();
        if(!username.equalsIgnoreCase(byId.get().getEmailId())){
            throw new UserNotFoundException("Bad credentials");
        }
        return ResponseEntity.ok(byId.get());

    }
}
