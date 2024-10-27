package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.Config.JwtService;
import com.hackathon.bankingapp.DTO.AccountResponseDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UsersService usersService;

    public ResponseEntity<?> getUserInfo(String authorization) {
        Optional<UserEntity> optionalUser = usersService.getUserByToken(authorization);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserResponseDTO(optionalUser.get()));
    }

    public ResponseEntity<?> getAccountInfo(String authorization) {
        Optional<UserEntity> optionalUser = usersService.getUserByToken(authorization);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AccountResponseDTO(optionalUser.get().getAccount()));
    }
}
