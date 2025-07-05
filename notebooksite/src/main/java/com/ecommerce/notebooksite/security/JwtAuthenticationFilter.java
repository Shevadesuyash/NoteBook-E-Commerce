package com.ecommerce.notebooksite.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtHelper jwtHelper;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
    this.jwtHelper = jwtHelper;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestHeader = request.getHeader("Authorization");
    log.info("Header : {}", requestHeader);

    String username = null;
    String token = null;

    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
      token = requestHeader.substring(7);
      try {
        username = this.jwtHelper.getUserNameFromToken(token);
      } catch (Exception e) {
        log.error("Error extracting username from token: {}", e.getMessage());
      }
    } else {
      log.warn("Authorization header is missing or does not start with Bearer");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      boolean isTokenValid = this.jwtHelper.validateToken(token, userDetails);
      if (isTokenValid) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      } else {
        log.warn("Token is not valid for user: {}", username);
      }
    }

    log.info("Filtering request: {}", request.getRequestURI());
    filterChain.doFilter(request, response);
  }
}
