package com.academiaSofftek.actividad5.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserEntity {
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 20, nullable = false)
    private String userName;
    @Column (length = 40, nullable = false)
    private String email;
    @Column (length = 60, nullable = false)
    private String password;
    @Column (columnDefinition = "boolean default true")
    private Boolean active;
}
