package com.dealersocket.idmsapi.security;
import com.dealersocket.idmsapi.jwt.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract token from query parameter
        String token = request.getParameter("token");
        System.out.println("token"+token);
//        System.out.println("JwtAuthenticationFilter username"+request.getParameter("username"));
//        System.out.println("JwtAuthenticationFilter password"+request.getParameter("password"));

        if (token != null) {
            try {
                String username = jwtUtil.extractUsername(token);

                // If token is valid, set authentication context
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, new java.util.ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                // Token validation failed, ignore or log the error
            }
        }

        filterChain.doFilter(request, response);
    }
}
