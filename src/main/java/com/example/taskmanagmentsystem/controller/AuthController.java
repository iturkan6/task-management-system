package com.example.taskmanagmentsystem.controller;

import com.example.taskmanagmentsystem.dto.AuthResponseDTO;
import com.example.taskmanagmentsystem.dto.UserLoginDTO;
import com.example.taskmanagmentsystem.dto.UserRequestDTO;
import com.example.taskmanagmentsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService service;

  @PostMapping("/register")
  public ResponseEntity<Void> registerUser(
          @RequestBody UserRequestDTO user) {
    service.register(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody UserLoginDTO user) {
    return ResponseEntity.ok(service.login(user));
  }
}
