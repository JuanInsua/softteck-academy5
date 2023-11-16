package com.academiaSofftek.actividad5.service.user;

import com.academiaSofftek.actividad5.model.Role;
import com.academiaSofftek.actividad5.model.enums.RolesNames;
import com.academiaSofftek.actividad5.repository.I_RoleRepository;
import com.academiaSofftek.actividad5.service.user.I_RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service class provides methods to manage roles and related operations.
 */
@Service
public class RoleServiceImpl implements I_RoleService {

    @Autowired
    I_RoleRepository roleRepository;

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to be found.
     * @return The found role.
     */
    @Override
    public Role findByName(RolesNames name) {
        return roleRepository.findByName(name);
    }

    /**
     * Finds a role by its Id
     *
     * @param id
     * @return The Found role
     */
    public Role findById (Long id){
        return roleRepository.findById(id).get();
    }
}