package com.example.taskmanagmentsystem.exception;

public class TaskNotFoundException extends RuntimeException{
  public TaskNotFoundException(Integer id) {
    super(String.format("Not found task with id -> %d", id));
  }
}
