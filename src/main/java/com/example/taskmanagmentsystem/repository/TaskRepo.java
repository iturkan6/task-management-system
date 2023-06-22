package com.example.taskmanagmentsystem.repository;

import com.example.taskmanagmentsystem.entity.Task;
import com.example.taskmanagmentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
}
