package com.academiaSofftek.actividad5.repository;

import com.academiaSofftek.actividad5.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This repository interface defines CRUD operations for UserEntity objects.
 */
@Repository
public interface I_UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Finds a user entity by its username.
     *
     * @param userName The username of the user to find.
     * @return An Optional containing the user entity if found, or an empty Optional if not found.
     */
    Optional<UserEntity> findByUserName(String userName);

    /**
     * Finds a user entity by its email.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the user entity if found, or an empty Optional if not found.
     */
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndActiveTrue(String email);
}
