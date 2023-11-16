package com.academiaSofftek.actividad5.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserDTO {
    @NotNull(message = "Name must not be null")
    private String nameTask;
    @NotNull(message = "Name must not be null")
    private String description;
}
