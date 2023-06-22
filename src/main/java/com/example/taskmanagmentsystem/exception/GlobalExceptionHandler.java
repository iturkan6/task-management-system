package com.example.taskmanagmentsystem.exception;

import com.example.taskmanagmentsystem.dto.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {
      UserNotFoundException.class
  })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  protected ResponseEntity<Object> handler(RuntimeException ex) {
    return new ResponseEntity<>(new ExceptionModel(
        ex.getMessage(),
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value()
    ), HttpStatus.NOT_FOUND);
  }
}
