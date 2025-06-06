package com.mauropot.portfolio.dto;

public record RegisterRequest(
    String username,
    String email,
    String password
) {}