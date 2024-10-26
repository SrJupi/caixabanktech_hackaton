package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.DTO.TokenDTO;
import com.hackathon.bankingapp.DTO.UserLoginDTO;
import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    public List<UserEntity> getUsersEntity(){
        return usersRepository.findAll();
    }

    public ResponseEntity<?> registerNewUser(UserRegisterDTO userRegisterDTO) {
        if (usersRepository.existsByEmail(userRegisterDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        if (usersRepository.existsByPhoneNumber(userRegisterDTO.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Phone number already exists");
        }
        UserEntity user = new UserEntity(userRegisterDTO);
        user = usersRepository.save(user);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    public ResponseEntity<?> loginUser(UserLoginDTO userLoginDto) {
        UserEntity user;
        try {
            UUID userUUID = UUID.fromString(userLoginDto.getIdentifier());
            user = usersRepository.findByAccountNumber(userUUID);
        }
        catch (IllegalArgumentException exception) {
            user = usersRepository.findByEmail(userLoginDto.getIdentifier());
        }
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found for the given identifier: " + userLoginDto.getIdentifier());
        }
        if (!BCrypt.checkpw(userLoginDto.getPassword(), user.getHashedPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
        }
        String token = "Token for " + user.getName();
        return ResponseEntity.ok(token);
    }
}
