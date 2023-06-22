package com.example.taskmanagmentsystem.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

  private final AuthenticationProvider authProvider;
  private final JwtAuthFilter filter;
  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers("/auth/**").permitAll()
//        .requestMatchers("/task/**").authenticated()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authProvider)
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
