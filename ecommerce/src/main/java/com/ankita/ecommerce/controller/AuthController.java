package com.ankita.ecommerce.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankita.ecommerce.dto.AuthResponse;
import com.ankita.ecommerce.dto.LoginRequest;
import com.ankita.ecommerce.repository.UserRepository;
import com.ankita.ecommerce.security.JwtUtil;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
private PasswordEncoder passwordEncoder;

@PostMapping("/login")
public AuthResponse login(@RequestBody LoginRequest request) {
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

  if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }
 String token = jwtUtil.generateToken(request.getEmail());

    return new AuthResponse(token);   
}
}