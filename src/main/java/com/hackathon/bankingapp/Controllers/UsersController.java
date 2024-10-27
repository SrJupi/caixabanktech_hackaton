package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.DTO.UserLoginDTO;
import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import com.hackathon.bankingapp.Services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return usersService.registerNewUser(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDto) {
        return usersService.loginUser(userLoginDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String auth) {
        return usersService.logoutUser(auth.substring(7));
    }
}
