package com.example.taskmanagmentsystem.controller;

import com.example.taskmanagmentsystem.dto.TaskRequestDTO;
import com.example.taskmanagmentsystem.entity.Task;
import com.example.taskmanagmentsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
  private final TaskService service;

  @PostMapping
  public ResponseEntity<List<Task>> search(@RequestBody TaskRequestDTO request) {
    return ResponseEntity.ok(service.search(request));
  }
}
