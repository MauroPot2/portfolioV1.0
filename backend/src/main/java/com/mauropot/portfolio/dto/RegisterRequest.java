package com.mauropot.portfolio.dto;

public record RegisterRequest (
    String email,
    String password,
    String firstName,
    String lastName
){}
