package com.nicolasperussi.burger.user.dto;

public record CreateClientRequest(
        String name,
        String email,
        String password,
        String phone
)
{}
