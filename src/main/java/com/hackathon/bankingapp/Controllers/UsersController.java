package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserEntity>> getUserEntity(){
        return ResponseEntity.ok(usersService.getUsersEntity());
    }

    @PostMapping("/api/users/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return ResponseEntity.ok(usersService.registerNewUser(userRegisterDTO));
    }
}
