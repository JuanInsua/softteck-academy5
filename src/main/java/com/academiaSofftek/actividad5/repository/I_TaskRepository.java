package com.academiaSofftek.actividad5.repository;

import com.academiaSofftek.actividad5.model.TaskEntity;
import com.academiaSofftek.actividad5.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_TaskRepository extends CrudRepository<TaskEntity, Long> {
    Page<TaskEntity>findByUserAndActiveTrue (UserEntity userEntity, Pageable pageable);
}
