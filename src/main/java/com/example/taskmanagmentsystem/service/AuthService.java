package com.example.taskmanagmentsystem.service;

import com.example.taskmanagmentsystem.dto.AuthResponseDTO;
import com.example.taskmanagmentsystem.dto.UserLoginDTO;
import com.example.taskmanagmentsystem.dto.UserRequestDTO;
import com.example.taskmanagmentsystem.entity.User;
import com.example.taskmanagmentsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtService jwtService;
  private final PasswordEncoder encoder;
  private final UserRepo userRepo;
  private final AuthenticationManager manager;

  public void register(UserRequestDTO userRequest) {
    User user = User.builder()
        .email(userRequest.email())
        .password(encoder.encode(userRequest.password()))
        .name(userRequest.name())
        .surname(userRequest.surname())
        .build();
    userRepo.save(user);
  }

  public AuthResponseDTO login(UserLoginDTO request) {
    manager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password())
    );
    User user = userRepo.findUserByEmail(request.email()).orElseThrow();
    String token = jwtService.generateToken(user);
    return new AuthResponseDTO(token);
  }
}
