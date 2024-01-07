package com.mstech.taskpomodoro.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtService {
    public String findUserName(String token){
        return exportToken(token, Claims::getSubject);
    }

    public boolean tokenControl(String token, UserDetails userDetails){
        final String userName = findUserName(token);
        return (userName.equals(userDetails.getUsername()) &&
                !exportToken(token, Claims::getExpiration).before(new Date()));
    }

    public String generateToken(String userName){
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*4))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(Authentication authentication){
        String userName = ((UserDetails)authentication.getPrincipal()).getUsername();
        return generateToken(userName);
    }

    private <T> T exportToken(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }

    private Key getKey(){
        byte[] key = Decoders.BASE64.decode(""); // TODO : .env ile secretkey koy ve şifreli
        return Keys.hmacShaKeyFor(key);
    }
}
