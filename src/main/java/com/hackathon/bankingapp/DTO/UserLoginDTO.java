package com.hackathon.bankingapp.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Getter
public class UserLoginDTO {
    private String identifier;
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }
}
