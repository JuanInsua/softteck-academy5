package com.academiaSofftek.actividad5.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDeleteDTO {
    @NotNull(message = "Name must not be null")
    private Long id;
    @NotNull(message = "Name must not be null")
    private String taskName;
    @NotNull(message = "Name must not be null")
    private String description;
    @NotNull(message = "Name must not be null")
    private Boolean active;
}
