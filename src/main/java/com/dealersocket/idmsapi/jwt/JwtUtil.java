package com.dealersocket.idmsapi.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Date;
@Component
public class JwtUtil {

    private String secretKey = "DriveSoft@@!";  // Secret key for signing JWTs
    private long expirationTime = 1000 * 60 * 60; // 1 hour expiration

    // Create JWT token
    public String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
    }

    // Extract claim from JWT token
    public <T> T extractClaim(String token, java.util.function.Function<DecodedJWT, T> claimsResolver) {
        DecodedJWT decodedJWT = decodeToken(token);
        return claimsResolver.apply(decodedJWT);
    }

    // Decode the JWT token
    private DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
    }

    // Validate if the token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration from JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token, DecodedJWT::getExpiresAt);
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }
}