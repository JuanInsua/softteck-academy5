package com.academiaSofftek.actividad5.service.user;

import com.academiaSofftek.actividad5.model.Role;
import com.academiaSofftek.actividad5.model.enums.RolesNames;
import org.springframework.stereotype.Service;

public interface I_RoleService  {
    Role findByName(RolesNames name);
    Role findById(Long id);
}
