package com.example.taskmanagmentsystem.dto;

public record UserRequestDTO(
    Integer id,
    String name,
    String surname,
    String email,
    String password
) {
}
