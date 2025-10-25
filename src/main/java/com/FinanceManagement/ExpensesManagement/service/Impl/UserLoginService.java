package com.FinanceManagement.ExpensesManagement.service.Impl;

import com.FinanceManagement.ExpensesManagement.entities.Users;
import com.FinanceManagement.ExpensesManagement.repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    UserRepositary userRepositary;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepositary.findByEmailId(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        UserDetails customUserDetail = new CustomUserDetail(users);
        return customUserDetail;


    }
}
