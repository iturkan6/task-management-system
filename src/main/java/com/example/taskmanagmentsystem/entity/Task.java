package com.example.taskmanagmentsystem.entity;

import com.example.taskmanagmentsystem.dto.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
  @Id
  private Integer id;
  @Column(nullable = false)
  private String title;
  private String description;
  private LocalDate dueDate;
  private TaskStatus status;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
