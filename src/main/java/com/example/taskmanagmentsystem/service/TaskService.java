package com.example.taskmanagmentsystem.service;

import com.example.taskmanagmentsystem.configuration.JwtAuthFilter;
import com.example.taskmanagmentsystem.dto.TaskRequestDTO;
import com.example.taskmanagmentsystem.entity.Task;
import com.example.taskmanagmentsystem.entity.User;
import com.example.taskmanagmentsystem.repository.TaskRepo;
import com.example.taskmanagmentsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo repo;
    private final UserRepo userRepo;
    private final JwtAuthFilter filter;

    public void createTask(TaskRequestDTO request) {
        User user = userRepo.findUserByEmail(filter.getEmail()).orElseThrow();
        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .dueDate(request.dueDate())
                .user(user)
                .build();
        repo.save(task);
    }

    public Task getTaskById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task updateTask(TaskRequestDTO request, Integer id) {
        Task task = Task.builder()
                .id(id)
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .dueDate(request.dueDate())
                .user(null)
                .build();
        repo.save(task);
        return task;
    }

    public void deleteTask(Integer id) {
            repo.deleteById(id);
    }
}
