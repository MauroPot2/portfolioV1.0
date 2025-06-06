package com.mauropot.portfolio.dto;

public record AuthRequest (
    String email,
    String password
) {}
