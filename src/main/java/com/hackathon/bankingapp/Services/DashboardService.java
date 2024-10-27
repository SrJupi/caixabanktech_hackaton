package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.DTO.AccountResponseDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    UsersService usersService;

    public ResponseEntity<?> getUserInfo() {
        //Check if logged in

        long id = 1;
        Optional<UserEntity> optionalUser = usersService.getUserById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserResponseDTO(optionalUser.get()));
    }

    public ResponseEntity<?> getAccountInfo() {
        //Check if logged in

        long id = 1;
        Optional<UserEntity> optionalUser = usersService.getUserById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AccountResponseDTO(optionalUser.get().getAccount()));
    }
}
