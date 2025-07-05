package com.ecommerce.notebooksite.controller;

import com.ecommerce.notebooksite.model.JwtRequest;
import com.ecommerce.notebooksite.model.JwtResponse;
import com.ecommerce.notebooksite.security.JwtHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserDetailsService userDetailsService;
  private final JwtHelper jwtHelper;
  private final AuthenticationManager authenticationManager;

  public AuthController(
      UserDetailsService userDetailsService,
      JwtHelper jwtHelper,
      AuthenticationManager authenticationManager) {
    this.userDetailsService = userDetailsService;
    this.jwtHelper = jwtHelper;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
    this.authenticate(request.getUsername(), request.getPassword());
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    String token = this.jwtHelper.generateToken(userDetails);
    JwtResponse response =
        JwtResponse.builder().username(userDetails.getUsername()).token(token).build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/user")
  public ResponseEntity<UserDetails> getUserDetails(
      @RequestHeader("Authorization") String tokenHeader) {
    String token = extractTokenFromHeader(tokenHeader);
    if (token != null) {
      String username = jwtHelper.getUserNameFromToken(token);
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      return new ResponseEntity<>(userDetails, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  private String extractTokenFromHeader(String tokenHeader) {
    if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
      return tokenHeader.substring(7);
    }
    return null;
  }

  private void authenticate(String username, String password) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    try {
      authenticationManager.authenticate(authenticationToken);
    } catch (BadCredentialsException ex) {
      throw new BadCredentialsException("Invalid UserName or Password");
    }
  }
}
