package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.Config.JwtService;
import com.hackathon.bankingapp.DTO.TokenDTO;
import com.hackathon.bankingapp.DTO.UserLoginDTO;
import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
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
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        UserEntity user = new UserEntity(userRegisterDTO);
        user = usersRepository.save(user);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    public ResponseEntity<?> loginUser(UserLoginDTO userLoginDto) {
        Optional<UserEntity> optionalUser;
        try {
            UUID userUUID = UUID.fromString(userLoginDto.getIdentifier());
            optionalUser = usersRepository.findByaccount_id(userUUID);
        }
        catch (IllegalArgumentException exception) {
            optionalUser = usersRepository.findByEmail(userLoginDto.getIdentifier());
        }
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found for the given identifier: " + userLoginDto.getIdentifier());
        }
        if (optionalUser.get().getHashedPassword().equals(passwordEncoder.encode(userLoginDto.getPassword()))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
        }
        TokenDTO token = new TokenDTO(jwtService.generateToken(optionalUser.get()));
        return ResponseEntity.ok(token);
    }

    public Optional<UserEntity> getUserById (long id) {
        return usersRepository.findById(id);
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Optional<UserEntity> getUserByToken(String token) {
        return getUserByEmail(jwtService.extractUsername(token));
    }

    public ResponseEntity<?> logoutUser(String auth) {
        Optional<UserEntity> optionalUser = getUserByToken(auth);
        UserEntity user = optionalUser.get();
        user.setLogout(new Date());
        usersRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
