package com.hackathon.bankingapp.DTO;

import com.hackathon.bankingapp.Entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {
    private String  name;
    private String  email;
    private String  phoneNumber;
    private String  address;
    private UUID    accountNumber;
    private String  hashedPassword;

    public UserResponseDTO(UserEntity user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.accountNumber = user.getAccountNumber();
        this.hashedPassword = user.getHashedPassword();
    }
}
