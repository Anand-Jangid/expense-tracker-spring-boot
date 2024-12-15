package com.anandjangid.expensetracker.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JWTInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil = new JWTUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
             String userId = jwtUtil.extractUserId(token);
             request.setAttribute("userId", userId);
             response.setHeader("userId", userId);
        }
        return true;
    }
}
