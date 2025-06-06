package com.mauropot.portfolio.service;

import com.mauropot.portfolio.dto.AuthRequest;
import com.mauropot.portfolio.dto.AuthResponse;
import com.mauropot.portfolio.dto.RegisterRequest;
import com.mauropot.portfolio.model.Role;
import com.mauropot.portfolio.model.User;
import com.mauropot.portfolio.repository.UserRepository;
import com.mauropot.portfolio.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered with ID: {}", savedUser.getId());
        return new AuthResponse(jwtUtil.generateToken(savedUser));
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.username(),  // Usa username invece di email
                request.password()
            )
        );
        
        var user = userRepository.findByUsername(request.username())
                .orElseThrow();
                
        return new AuthResponse(jwtUtil.generateToken(user));
    }
}