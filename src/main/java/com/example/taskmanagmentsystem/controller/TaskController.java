package com.example.taskmanagmentsystem.controller;

import com.example.taskmanagmentsystem.dto.TaskRequestDTO;
import com.example.taskmanagmentsystem.entity.Task;
import com.example.taskmanagmentsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskRequestDTO request) {
        service.createTask(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @GetMapping("get")
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateById(
            @PathVariable Integer id,
            @RequestBody TaskRequestDTO request) {
        return ResponseEntity.ok(service.updateTask(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTaskById(
            @PathVariable Integer id
    ) {
        service.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
