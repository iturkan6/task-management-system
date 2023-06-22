package com.example.taskmanagmentsystem.service;

import com.example.taskmanagmentsystem.configuration.JwtAuthFilter;
import com.example.taskmanagmentsystem.dto.TaskRequestDTO;
import com.example.taskmanagmentsystem.dto.TaskStatus;
import com.example.taskmanagmentsystem.entity.Task;
import com.example.taskmanagmentsystem.entity.User;
import com.example.taskmanagmentsystem.exception.TaskNotFoundException;
import com.example.taskmanagmentsystem.exception.UserNotFoundException;
import com.example.taskmanagmentsystem.repository.TaskRepo;
import com.example.taskmanagmentsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepo repo;
  private final UserRepo userRepo;
  private final JwtAuthFilter filter;

  public void createTask(TaskRequestDTO request) {
    User user = userRepo.findUserByEmail(filter.getEmail()).orElseThrow(UserNotFoundException::new);
    Task task = Task.builder()
        .title(request.title())
        .description(request.description())
        .status(TaskStatus.valueOf(request.status()))
        .dueDate(request.dueDate())
        .user(user)
        .build();
    repo.save(task);
  }

  public Task getTaskById(Integer id) {
    return repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
  }

  public List<Task> getAllTasks() {
    return repo.findAll();
  }

  public Task updateTask(TaskRequestDTO request, Integer id) {
    Task task = Task.builder()
        .id(id)
        .title(request.title())
        .description(request.description())
        .status(TaskStatus.valueOf(request.status()))
        .dueDate(request.dueDate())
        .user(null)
        .build();
    repo.save(task);
    return task;
  }

  public void deleteTask(Integer id) {
    repo.deleteById(id);
  }

  public List<Task> search(TaskRequestDTO request) {
    Task task = Task.builder()
        .title(request.title())
        .description(request.description())
        .status(TaskStatus.valueOf(request.status()))
        .build();
    return repo.findAll(Example.of(task));
  }
}
