package com.academiaSofftek.actividad5.service.user;

import com.academiaSofftek.actividad5.dto.user.RegisterUserDTO;
import com.academiaSofftek.actividad5.model.UserEntity;

import java.util.Optional;

public interface I_UserService {
    RegisterUserDTO save(RegisterUserDTO user);
    Optional<UserEntity> findByEmail(String email);
}
