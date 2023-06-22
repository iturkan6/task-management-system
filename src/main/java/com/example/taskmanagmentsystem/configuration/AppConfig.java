package com.example.taskmanagmentsystem.configuration;

import com.example.taskmanagmentsystem.entity.User;
import com.example.taskmanagmentsystem.exception.UserNotFoundException;
import com.example.taskmanagmentsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

  private final UserRepo repo;

  @Bean
  public UserDetailsService userDetailsService() {
    return email -> {
      User user = repo.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
      return new org.springframework.security.core.userdetails.User(
          user.getUsername(),
          user.getPassword(),
          user.getAuthorities()
      );
    };
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    var provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(encoder());
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
