package com.academiaSofftek.actividad5.repository;

import com.academiaSofftek.actividad5.model.Role;
import com.academiaSofftek.actividad5.model.enums.RolesNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository interface defines database operations for Role entities.
 */
@Repository
public interface I_RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to find.
     * @return The Role object if found, or null if not found.
     */
    Role findByName(RolesNames name);
}
