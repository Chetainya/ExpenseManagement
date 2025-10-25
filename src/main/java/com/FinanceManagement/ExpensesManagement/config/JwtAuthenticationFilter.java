package com.FinanceManagement.ExpensesManagement.config;

import com.FinanceManagement.ExpensesManagement.service.Impl.JwtService;
import com.FinanceManagement.ExpensesManagement.service.Impl.UserLoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserLoginService userLoginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String autheHeader=request.getHeader("Authorization");
        if(autheHeader!=null && autheHeader.startsWith("Bearer")){
            String token=autheHeader.substring(7);
            if(jwtService.validateToken(token)){
                String userNameFromToken = jwtService.getUserNameFromToken(token);
                UserDetails userDetails = userLoginService.loadUserByUsername(userNameFromToken);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                if(SecurityContextHolder.getContext().getAuthentication()==null){
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);

    }
}
