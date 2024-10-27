package com.hackathon.bankingapp.DTO;

import lombok.Getter;

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
