package com.hackathon.bankingapp.Entities;

import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Users")
public class UserEntity {

    @Id
    @SequenceGenerator(name="users_seq", sequenceName = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_seq")
    private Long    userId;
    private String  name;
    private String  email;
    private String  phoneNumber;
    private String  address;
    private String  hashedPassword;
    private UUID    accountNumber;

    public UserEntity() {
    }

    public UserEntity(String name, String email, String phoneNumber, String address, String hashedPassword) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.hashedPassword = hashedPassword;
    }

    public UserEntity(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.getName();
        this.email = userRegisterDTO.getEmail();
        this.phoneNumber = userRegisterDTO.getPhoneNumber();
        this.address = userRegisterDTO.getAddress();
        this.hashedPassword = BCrypt.hashpw(userRegisterDTO.getPassword(), BCrypt.gensalt());
        this.accountNumber = UUID.randomUUID();
    }
}
