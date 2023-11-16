package com.academiaSofftek.actividad5.service.user;
import com.academiaSofftek.actividad5.dto.user.RegisterUserDTO;
import com.academiaSofftek.actividad5.exeption.CustomedHandler;
import com.academiaSofftek.actividad5.exeption.UserAlreadyExistException;
import com.academiaSofftek.actividad5.model.Role;
import com.academiaSofftek.actividad5.model.UserEntity;
import com.academiaSofftek.actividad5.repository.I_UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * Implementation of the {@link I_UserService} interface, providing authentication
 * and user-related operations for the application.
 *
 * @Service Indicates that this class is a Spring service component.
 */
@Service
public class AuthServiceImpl implements I_UserService {

    /**
     * Autowired field for the user repository, providing data access methods.
     */
    @Autowired
    I_UserRepository userRepository;

    /**
     * Autowired field for the ModelMapper, facilitating object mapping.
     */
    @Autowired
    ModelMapper modelMapper;

    /**
     * Autowired field for the role service, providing role-related business logic.
     */
    @Autowired
    I_RoleService roleService;

    /**
     * Autowired field for the password encoder, providing password encryption functionality.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Saves a new user based on the provided RegisterUserDTO.
     *
     * @param user The DTO containing information for creating a new user.
     * @return The saved RegisterUserDTO.
     * @throws UserAlreadyExistException if a user with the same email already exists.
     */
    @Transactional
    @Override
    public RegisterUserDTO save(RegisterUserDTO user) throws UserAlreadyExistException {

        List<Role> userRoles = new ArrayList<>();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(setRole(user.getRoles()));
        userEntity.setActive(true);

        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Este Usuario ya existe");
        } else {
            try {
                userRepository.save(userEntity);
            } catch (Exception e) {
                throw new CustomedHandler("Error saving user on the database");
            }
        }
        return user;
    }

    /**
     * Retrieves a user by email, ensuring that the user is active.
     *
     * @param email The email of the user to retrieve.
     * @return An Optional containing the user entity if found and active.
     */
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).get();
        Optional<UserEntity> userEntity = null;

        if (user.getActive()) {
            userEntity = userRepository.findByEmail(email);
        }
        return userEntity;
    }

    /**
     * Sets roles for a user based on the provided role IDs.
     *
     * @param roles The list of role IDs to set for the user.
     * @return A list of Role entities corresponding to the provided role IDs.
     * @throws CustomedHandler if there is an error adding roles to the user.
     */
    private List<Role> setRole(List<Long> roles) {
        List<Role> aux = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            try {
                aux.add(this.roleService.findById(roles.get(i)));
            } catch (Exception e) {
                throw new CustomedHandler("Error adding role to the user");
            }
        }
        return aux;
    }
    private Random random = new Random();
    /**
     * Generates a random long ID.
     *
     * @return A randomly generated long ID.
     */
    private long generateRandomId() {
        return random.nextLong();
    }
}

