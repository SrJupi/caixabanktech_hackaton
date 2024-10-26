package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.DTO.TokenDTO;
import com.hackathon.bankingapp.DTO.UserLoginDTO;
import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getUserEntity(){
        return ResponseEntity.ok(usersService.getUsersEntity());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return usersService.registerNewUser(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDto) {
        return usersService.loginUser(userLoginDto);
    }
}
