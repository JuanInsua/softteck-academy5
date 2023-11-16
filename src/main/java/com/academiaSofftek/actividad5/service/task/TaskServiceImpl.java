package com.academiaSofftek.actividad5.service.task;

import com.academiaSofftek.actividad5.config.SecurityConfig;
import com.academiaSofftek.actividad5.dto.task.TaskCreateDTO;
import com.academiaSofftek.actividad5.dto.task.TaskDeleteDTO;
import com.academiaSofftek.actividad5.dto.task.TaskModifyDTO;
import com.academiaSofftek.actividad5.exeption.CustomedHandler;
import com.academiaSofftek.actividad5.exeption.TaskNotFoundException;
import com.academiaSofftek.actividad5.model.TaskEntity;
import com.academiaSofftek.actividad5.model.UserEntity;
import com.academiaSofftek.actividad5.repository.I_TaskRepository;
import com.academiaSofftek.actividad5.service.user.UserEntityService;
import com.academiaSofftek.actividad5.service.user.I_UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementation of the {@link I_TaskService} interface, providing business logic
 * for task-related operations and interactions with the data layer.
 *
 * @Service Indicates that this class is a Spring service component.
 */
@Service
public class TaskServiceImpl implements I_TaskService {

    /**
     * Autowired field for the task repository, providing data access methods.
     */
    @Autowired
    private I_TaskRepository taskRepository;

    /**
     * Autowired field for the user service, providing user-related business logic.
     */
    @Autowired
    private I_UserService userService;

    /**
     * Autowired field for the user entity service, providing user entity-related operations.
     */
    @Autowired
    private UserEntityService userEntityService;

    /**
     * Autowired field for the security configuration, handling security-related functionality.
     */
    @Autowired
    private SecurityConfig securityConfig;

    /**
     * Autowired field for the ModelMapper, facilitating object mapping.
     */
    @Autowired
    ModelMapper modelMapper;

    /**
     * Saves a new task based on the provided TaskCreateDTO.
     *
     * @param taskCreateDTO The DTO containing information for creating a new task.
     * @return The saved TaskEntity.
     */
    @Transactional
    @Override
    public TaskEntity save(TaskCreateDTO taskCreateDTO) {
        TaskEntity task = modelMapper.map(taskCreateDTO, TaskEntity.class);
        task.setActive(true);
        task.setUser(userEntityService.findByEmail(securityConfig.getUserNameFromToken()).get());
        return taskRepository.save(task);
    }

    /**
     * Retrieves all tasks for the currently authenticated user with pagination support.
     *
     * @param pageable Object defining the page and size for pagination.
     * @return A Page object containing a list of TaskEntity for the current user.
     * @throws CustomedHandler if there is an error retrieving user details from the database.
     */
    @Override
    public Page<TaskEntity> getAll(Pageable pageable) {
        String currentUser = securityConfig.getUserNameFromToken();
        UserEntity currentUserEntity;
        Page<TaskEntity> taskPaged = null;

        try {
            currentUserEntity = userService.findByEmail(currentUser).get();
            taskPaged = taskRepository.findByUserAndActiveTrue(currentUserEntity, pageable);
        } catch (Exception e) {
            throw new CustomedHandler("Error retrieving user details from the database");
        }

        return taskPaged;
    }

    /**
     * Updates an existing task based on the provided TaskModifyDTO.
     *
     * @param taskModifyDTO The DTO containing information for modifying an existing task.
     * @return The updated TaskEntity.
     * @throws TaskNotFoundException if the task with the specified ID is not found.
     */
    @Override
    public TaskEntity update(TaskModifyDTO taskModifyDTO) {
        Optional<TaskEntity> existingTaskOptional = taskRepository.findById(taskModifyDTO.getId());

        if (existingTaskOptional.isPresent()) {
            TaskEntity existingTask = existingTaskOptional.get();

            // Update only the modified properties
            if (taskModifyDTO.getDescription() != null) {
                existingTask.setDescription(taskModifyDTO.getDescription());
            }

            if (taskModifyDTO.getTaskName() != null) {
                existingTask.setTaskName(taskModifyDTO.getTaskName());
            }

            // Save the updated task
            return taskRepository.save(existingTask);
        } else {
            // Handle the situation when the task does not exist
            throw new TaskNotFoundException("No se encontró la tarea con ID: " + taskModifyDTO.getId());
        }
    }

    /**
     * Marks a task as inactive or active based on its current state.
     *
     * @param idTask The ID of the task to be marked as inactive or active.
     * @return The updated TaskEntity.
     * @throws TaskNotFoundException if the task with the specified ID is not found.
     */
    @Override
    public TaskEntity eliminar(Long idTask) {
        Optional<TaskEntity> existingTaskOptional = taskRepository.findById(idTask);

        if (existingTaskOptional.isPresent()) {
            TaskEntity existingTask = existingTaskOptional.get();

            // Update only the modified properties
            if (existingTask.getActive()) {
                existingTask.setActive(false);
            } else {
                existingTask.setActive(true);
            }

            // Save the updated task
            return taskRepository.save(existingTask);
        } else {
            // Handle the situation when the task does not exist
            throw new TaskNotFoundException("No se encontró la tarea con ID: " + idTask);
        }
    }
}

