package com.academiaSofftek.actividad5.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponseUserDTO {
    private String userName;
    private List<String> roles;
}
