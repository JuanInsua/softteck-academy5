package com.academiaSofftek.actividad5.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity user;
    @Column(length = 20, nullable = false)
    private String taskName;
    @Column (length = 120, nullable = false)
    private String description;
    @Column (columnDefinition = "boolean default true")
    private Boolean active;
}
