package com.example.taskmanagmentsystem.dto;

import java.time.LocalDate;

public record TaskRequestDTO(
        Integer id,
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status
) {
}
