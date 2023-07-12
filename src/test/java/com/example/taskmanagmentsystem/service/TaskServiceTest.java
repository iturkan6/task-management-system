package com.example.taskmanagmentsystem.service;

import com.example.taskmanagmentsystem.configuration.JwtAuthFilter;
import com.example.taskmanagmentsystem.dto.TaskRequestDTO;
import com.example.taskmanagmentsystem.dto.TaskStatus;
import com.example.taskmanagmentsystem.entity.Task;
import com.example.taskmanagmentsystem.entity.User;
import com.example.taskmanagmentsystem.exception.TaskNotFoundException;
import com.example.taskmanagmentsystem.exception.UserNotFoundException;
import com.example.taskmanagmentsystem.repository.TaskRepo;
import com.example.taskmanagmentsystem.repository.UserRepo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

private TaskService taskService;

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private JwtAuthFilter jwtAuthFilter;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepo, userRepo, jwtAuthFilter);
    }

    @AfterEach
    @SneakyThrows
    void close(){
        this.closeable.close();
    }


    @Test
    void createTask_ValidRequest_TaskCreated() {
        String email = "user@example.com";
        TaskRequestDTO request = new TaskRequestDTO(
                "Task 1",
                "Description 1",
                LocalDate.now(),
                "IN_PROGRESS"

        );
        User user = new User();
        user.setEmail(email);

        when(jwtAuthFilter.getEmail()).thenReturn(email);
        when(userRepo.findUserByEmail(email)).thenReturn(Optional.of(user));
        when(taskRepo.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> taskService.createTask(request));

        verify(userRepo).findUserByEmail(email);
        verify(taskRepo).save(any(Task.class));
    }

    @Test
    void createTask_InvalidUser_ThrowsUserNotFoundException() {
        String email = "user@example.com";
        TaskRequestDTO request = new TaskRequestDTO(
                "Task 1",
                "Description 1",
                LocalDate.now(),
                "IN_PROGRESS"

        );

        when(jwtAuthFilter.getEmail()).thenReturn(email);
        when(userRepo.findUserByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> taskService.createTask(request));
    }

    @Test
    void getTaskById_ExistingTask_ReturnsTask() {
        int taskId = 1;
        Task task = new Task();
        task.setId(taskId);

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertEquals(task, result);
        verify(taskRepo).findById(taskId);
    }

    @Test
    void getTaskById_NonExistingTask_ThrowsTaskNotFoundException() {
        int taskId = 1;

        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    void getAllTasks_ReturnsAllTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());

        when(taskRepo.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(tasks, result);
        verify(taskRepo).findAll();
    }

    @Test
    void updateTask_ValidRequest_TaskUpdated() {
        int taskId = 1;
        TaskRequestDTO request = new TaskRequestDTO(
                "Updated Task",
                "Updated Description",
                LocalDate.now(),
                "DONE"
        );

        when(taskRepo.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.updateTask(request, taskId);

        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals(request.title(), result.getTitle());
        assertEquals(request.description(), result.getDescription());
        assertEquals(TaskStatus.DONE, result.getStatus());
        assertEquals(request.dueDate(), result.getDueDate());
        assertNull(result.getUser());
        verify(taskRepo).save(any(Task.class));
    }

    @Test
    void deleteTask_ExistingTask_TaskDeleted() {
        int taskId = 1;

        assertDoesNotThrow(() -> taskService.deleteTask(taskId));

        verify(taskRepo).deleteById(taskId);
    }

    @Test
    void search_ReturnsMatchingTasks() {
        TaskRequestDTO request = TaskRequestDTO.builder()
                .title("Task 2")
                .description("Description 2")
                .status("DONE")
                .build();
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setStatus(TaskStatus.IN_PROGRESS);
        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setStatus(TaskStatus.DONE);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepo.findAll(any(Example.class))).thenReturn(tasks);

        List<Task> result = taskService.search(request);

        assertEquals(1, result.size());
        assertEquals(task1, result.get(0));
        verify(taskRepo).findAll(any(Example.class));
    }

}