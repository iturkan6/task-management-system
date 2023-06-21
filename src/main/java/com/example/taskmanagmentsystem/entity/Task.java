package com.example.taskmanagmentsystem.entity;

import com.example.taskmanagmentsystem.dto.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
  @Id
  Integer id;
  @Column(nullable = false)
  String title;
  String description;
  LocalDate dueDate;
  TaskStatus status;
}
