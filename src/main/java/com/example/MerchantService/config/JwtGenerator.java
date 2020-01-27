package com.example.MerchantService.config;

import com.example.MerchantService.entity.Merchant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class JwtGenerator {
    @Value("${jwt.secret}")
    private String secret;
    public String parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            String id = (String) body.get("userId");
            return id;
        } catch (JwtException | ClassCastException e) {
            e.printStackTrace();
            return  null;
        }
    }
    public String generateToken(Merchant u) {
        Claims claims = Jwts.claims().setSubject(u.getName());
        claims.put("userId", u.getId() + "");
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
