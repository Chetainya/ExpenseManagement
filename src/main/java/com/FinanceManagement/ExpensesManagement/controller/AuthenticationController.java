package com.FinanceManagement.ExpensesManagement.controller;


import com.FinanceManagement.ExpensesManagement.dto.AuthRequestDto;
import com.FinanceManagement.ExpensesManagement.dto.JwtResponse;
import com.FinanceManagement.ExpensesManagement.dto.RefreshTokenDto;
import com.FinanceManagement.ExpensesManagement.dto.RequestDTO;
import com.FinanceManagement.ExpensesManagement.entities.Users;
import com.FinanceManagement.ExpensesManagement.exceptions.UserNotFoundException;
import com.FinanceManagement.ExpensesManagement.repositary.UserRepositary;
import com.FinanceManagement.ExpensesManagement.service.Impl.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    ObjectMapper objectMapper;



    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepositary userRepositary;

    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService,UserRepositary userRepositary) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.userRepositary=userRepositary;
    }

    @PostMapping("/login")
    public ResponseEntity<? > login(@RequestBody RequestDTO requestDTO){
        AuthRequestDto authRequestDto = objectMapper.convertValue(requestDTO.getApiData(), AuthRequestDto.class);
        Optional<Users> userFromDB=userRepositary.findByEmailId(authRequestDto.getEmailId());

        if(userFromDB.isEmpty()){
            throw new UserNotFoundException();
        }

        Users users = userFromDB.get();


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmailId(),authRequestDto.getPassword()));

        String accessToken = jwtService.generateToken(users.getEmailId(), true);
        String refreshToken = jwtService.generateToken(users.getEmailId(), false);

        JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, users);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/login/refresh")
    public ResponseEntity<? > refreshToken(@RequestBody RequestDTO requestDTO){
        RefreshTokenDto refreshTokenDto = objectMapper.convertValue(requestDTO.getApiData(), RefreshTokenDto.class);

        if(jwtService.validateToken(refreshTokenDto.refreshToken())){
            String userNameFromToken = jwtService.getUserNameFromToken(refreshTokenDto.refreshToken());

            String accessToken = jwtService.generateToken(userNameFromToken, true);
            String refreshToken = jwtService.generateToken(userNameFromToken, false);
            Users users=userRepositary.findByEmailId(userNameFromToken).get();
            JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, users);
            return ResponseEntity.ok(jwtResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");

    }
}
