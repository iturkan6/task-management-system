package com.example.taskmanagmentsystem.dto;

public record UserRequestDTO(
    String name,
    String surname,
    String email,
    String password
) {
}
