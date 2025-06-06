package com.mauropot.portfolio.dto;

public record AuthRequest(
    String username,  // Modificato da email a username
    String password
) {}