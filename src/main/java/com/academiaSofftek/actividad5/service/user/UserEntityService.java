package com.academiaSofftek.actividad5.service.user;
import com.academiaSofftek.actividad5.model.UserEntity;
import com.academiaSofftek.actividad5.repository.I_UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserEntityService {

    @Autowired
    I_UserRepository userRepository;
    public Optional<UserEntity> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<UserEntity> findByEmailAndActiveTrue(String email){

        return userRepository.findByEmailAndActiveTrue(email);

    }
    public void save(UserEntity userEntity){

        this.userRepository.save(userEntity);
    }

    public Optional<UserEntity> findById(Long id){
        return this.userRepository.findById(id);
    }
}
