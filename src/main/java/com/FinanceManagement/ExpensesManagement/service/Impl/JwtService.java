package com.FinanceManagement.ExpensesManagement.service.Impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService  {


    private static final String SECRET_KEY="HKDHFHWHFUWFIUEWUEFUWEYFRHDWHFWEHFUWE";

    private static final long EXPIRATION_TOKEN_TIME=60*60*1000;

    private static final long EXPIRATION_REFRESH_TIME=48*60*60*1000;


    public String generateToken(String username,boolean isAccessToken){

        long expiration = isAccessToken ? EXPIRATION_TOKEN_TIME : EXPIRATION_REFRESH_TIME;

        return Jwts.builder().subject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();

    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }


}
