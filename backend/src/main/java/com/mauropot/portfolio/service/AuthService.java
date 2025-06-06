package com.mauropot.portfolio.service;

import com.mauropot.portfolio.dto.AuthRequest;
import com.mauropot.portfolio.dto.AuthResponse;
import com.mauropot.portfolio.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}