package com.ankita.ecommerce.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // COMPLETE BYPASS FOR SWAGGER + AUTH
        if (path.startsWith("/v3/api-docs") ||
        path.startsWith("/v3/api-docs/**") ||
           path.startsWith("/swagger-ui")  ||
           path.startsWith("/swagger-ui.html")||
            path.startsWith("/auth") ||
            path.startsWith("/users/register")) {

            filterChain.doFilter(request, response);
            return;
            }
        String header = request.getHeader("Authorization");
        //  IMPORTANT SAFE CHECK
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = header.substring(7);
            String email = jwtUtil.extractEmail(token);
 if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
 UserDetails userDetails = userDetailsService.loadUserByUsername(email);
  UsernamePasswordAuthenticationToken auth =
                         new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            // token invalid ho to bhi request break nahi hogi
        }
        filterChain.doFilter(request, response);
    }
}