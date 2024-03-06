package com.yudiol.securityfirst.util;

import com.yudiol.securityfirst.model.Role;
import com.yudiol.securityfirst.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public String generateToken(UserDetails userDetails) {
        // Логика генерации JWT
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", roles);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }


    public User parseToken(String token) {
        try {
            Claims body = getAllClaimsFromToken(token);
            User user = new User();
            user.setUsername(body.getSubject());
            user.setUserId(Long.parseLong((String) body.get("userId")));
            user.setRole((Role) body.get("role"));

            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        return !getAllClaimsFromToken(token).isEmpty();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

//    public UserDetails extractUSerDetails(String token){
//        Map<String,Object> claims = getAllClaimsFromToken(token);
//
//        new UserDetails(claims.getS);
//    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
