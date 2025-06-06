package com.mauropot.portfolio.service;

import com.mauropot.portfolio.dto.AuthRequest;
import com.mauropot.portfolio.dto.AuthResponse;
import com.mauropot.portfolio.dto.RegisterRequest;
import com.mauropot.portfolio.exception.EmailAlreadyExistsException;
import com.mauropot.portfolio.model.Role;
import com.mauropot.portfolio.model.User;
import com.mauropot.portfolio.repository.UserRepository;
import com.mauropot.portfolio.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("Registrazione nuovo utente: {}", request.email());
        
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email già registrata: " + request.email());
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        try {
            User savedUser = userRepository.save(user);
            log.info("Utente registrato con ID: {}", savedUser.getId());
            return new AuthResponse(jwtUtil.generateToken(savedUser));
        } catch (Exception e) {
            log.error("Errore durante la registrazione", e);
            throw new RuntimeException("Errore durante la registrazione", e);
        }
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        log.info("Tentativo di login per: {}", request.email());
        
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
            );
            
            var user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
            
            log.info("Login riuscito per: {}", request.email());
            // Ensure user implements UserDetails or adapt as needed
            return new AuthResponse(jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) user));
            
        } catch (BadCredentialsException e) {
            log.warn("Credenziali non valide per: {}", request.email());
            throw new BadCredentialsException("Credenziali non valide");
        }
    }
}