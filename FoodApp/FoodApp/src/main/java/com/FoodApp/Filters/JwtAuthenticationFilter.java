package com.FoodApp.Filters;

import com.FoodApp.Util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        logger.info("--- JWT Filter Invoked ---");
        logger.info("Requested Path: {}", path);

        // Skip token check for public endpoints
        if (path.equals("/api/login") || path.equals("/api/register") || path.startsWith("/api/foods")) {
            logger.info("Skipping JWT check for public endpoint.");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        logger.info("Authorization Header: {}", authHeader);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            logger.info("Extracted JWT: {}", token);

            try {
                String email = jwtUtil.extractUsername(token);
                logger.info("Extracted email from token: {}", email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    logger.info("No existing authentication. Loading user details...");
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    logger.info("User loaded: {}", userDetails.getUsername());

                    boolean isValid = jwtUtil.validateToken(token, userDetails);
                    logger.info("Is token valid? {}", isValid);

                    if (isValid) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        logger.info("SecurityContext successfully updated with authenticated user.");
                    } else {
                        logger.warn("Token validation failed.");
                    }
                } else {
                    logger.info("Email null or already authenticated.");
                }
            } catch (Exception e) {
                logger.error("JWT Filter error: {} - {}", e.getClass().getSimpleName(), e.getMessage());
            }
        } else {
            logger.info("No Bearer token found or header invalid.");
        }

        logger.info("Final SecurityContext Authentication: {}", SecurityContextHolder.getContext().getAuthentication());
        logger.info("--- End of JWT Filter ---");

        filterChain.doFilter(request, response);
    }
}