package com.academiaSofftek.actividad5.dto.task;

import com.academiaSofftek.actividad5.model.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * A Data Transfer Object (DTO) to return paged Task list
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksListPagedDTO {

        private int totalPages;
        private int taskPerPage;
        private long totalTasks;
        private int currentPage;
        private List<TaskEntity> tasks;

        /**
         * Constructor to fill DTO attributes with Page info.
         *
         * @param page type: Page<>, comes from Pageable
         */
        public TasksListPagedDTO(Page<TaskEntity> page) {
            ModelMapper modelMapper = new ModelMapper();
            this.totalPages = page.getTotalPages();
            this.taskPerPage = page.getNumberOfElements();
            this.totalTasks = page.getTotalElements();
            this.currentPage = page.getNumber();
            this.tasks = page.getContent().stream().
                    map(a->modelMapper.map(a, TaskEntity.class)).toList();
        }
}
