package com.example.foodshopbe.components;

import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.secretKey}")
    private String secretKey;
    public String generateToken (User user) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginId", user.getLoginId());
        claims.put("userId", user.getId());
        try {
                    String token = Jwts
                    .builder()
                    .setClaims(claims)
                    .setSubject(user.getLoginId())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

            return  token;
        } catch (Exception e) {
            throw  new InvalidParamException("Khong the tao token , error: " + e.getMessage());
        }
    };

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public  boolean isTokenExpired(String token) {
        java.util.Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new java.util.Date());
    }

    public String extractLoginId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String loginId = extractLoginId(token);
        return (loginId.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
