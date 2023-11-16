package com.academiaSofftek.actividad5.controller;

import com.academiaSofftek.actividad5.config.SecurityConfig;
import com.academiaSofftek.actividad5.dto.task.TaskCreateDTO;
import com.academiaSofftek.actividad5.dto.task.TaskModifyDTO;
import com.academiaSofftek.actividad5.dto.task.TasksListPagedDTO;
import com.academiaSofftek.actividad5.exeption.CustomedHandler;
import com.academiaSofftek.actividad5.model.TaskEntity;
import com.academiaSofftek.actividad5.service.task.I_TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling user-related operations and task management.
 * This class is responsible for processing HTTP requests related to user tasks.
 *
 * @RestController Indicates that this class is a controller providing RESTful endpoints.
 * @CrossOrigin Enables Cross-Origin Resource Sharing (CORS) for the specified origins.
 * @RequestMapping("/api/v1/user") Base mapping for all endpoints in this controller.
 * @AllArgsConstructor Lombok annotation to generate a constructor with all required fields.
 * @NoArgsConstructor Lombok annotation to generate a default constructor.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    /**
     * Autowired field for the TaskService, responsible for task-related business logic.
     */
    @Autowired
    private I_TaskService taskService;

    /**
     * Autowired field for the SecurityConfig, responsible for security-related configurations.
     */
    @Autowired
    private SecurityConfig securityConfig;

    /**
     * Endpoint for creating a new task.
     *
     * @param taskCreateDTO The DTO containing information for creating a new task.
     * @return ResponseEntity indicating the success or failure of the task creation.
     */
    @Transactional
    @PostMapping("/newTask")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        try {
            this.taskService.save(taskCreateDTO);
        } catch (DataAccessException dae) {
            throw new CustomedHandler("Error persisting Task" + dae.getMessage());
        }
        return new ResponseEntity<>("Task created successfully", HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving all tasks with pagination support.
     *
     * @param page The page number for pagination.
     * @param size The number of tasks per page.
     * @return TasksListPagedDTO containing a page of tasks.
     */
    @GetMapping("/getTasks")
    public TasksListPagedDTO getAllTasks(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskEntity> tasks = taskService.getAll(pageable);
        return new TasksListPagedDTO(tasks);
    }

    /**
     * Endpoint for user logout, invalidating the current token.
     *
     * @param request The HttpServletRequest for the current user session.
     * @return ResponseEntity indicating successful logout.
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        securityConfig.invalidateToken();
        return ResponseEntity.ok("Logout successful");
    }

    /**
     * Endpoint for modifying an existing task.
     *
     * @param taskModifyDTO The DTO containing information for modifying an existing task.
     * @return ResponseEntity indicating the success or failure of the task modification.
     */
    @PostMapping("/editTask")
    public ResponseEntity<?> modifyTask(@Valid @RequestBody TaskModifyDTO taskModifyDTO) {
        try {
            this.taskService.update(taskModifyDTO);
        } catch (DataAccessException dae) {
            throw new CustomedHandler("Error persisting Task" + dae.getMessage());
        }
        return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a task by its ID.
     *
     * @param id The ID of the task to be deleted.
     * @return ResponseEntity indicating the success or failure of the task deletion.
     */
    @PostMapping("/bajaTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            this.taskService.eliminar(id);
        } catch (DataAccessException dae) {
            throw new CustomedHandler("Error deleting Task" + dae.getMessage());
        }
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
    }
}

