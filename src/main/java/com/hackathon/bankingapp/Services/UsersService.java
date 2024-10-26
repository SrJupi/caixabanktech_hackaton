package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;
    public List<UserEntity> getUsersEntity(){
        return userRepository.findAll();
    }

    public UserResponseDTO registerNewUser(UserRegisterDTO userRegisterDTO) {
        UserEntity user = new UserEntity(userRegisterDTO);
        user = userRepository.save(user);
        return new UserResponseDTO(user);
    }
}
