package com.academiaSofftek.actividad5.service.task;

import com.academiaSofftek.actividad5.dto.task.TaskCreateDTO;
import com.academiaSofftek.actividad5.dto.task.TaskDeleteDTO;
import com.academiaSofftek.actividad5.dto.task.TaskModifyDTO;
import com.academiaSofftek.actividad5.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


public interface I_TaskService {
    TaskEntity save (TaskCreateDTO taskCreateDTO);
    Page<TaskEntity> getAll(Pageable pageable);
    TaskEntity update(TaskModifyDTO taskModifyDTO);
    TaskEntity eliminar (Long id);

}
