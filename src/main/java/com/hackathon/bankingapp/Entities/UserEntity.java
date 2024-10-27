package com.hackathon.bankingapp.Entities;

import com.hackathon.bankingapp.DTO.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Long    userId;
    private String  name;
    private String  email;
    private String  phoneNumber;
    private String  address;
    private String  hashedPassword;
    private Date    logout;

    @Enumerated(EnumType.STRING)
    private Role    role;
    private String  pin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    public UserEntity() {
    }

    public UserEntity(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.getName();
        this.email = userRegisterDTO.getEmail();
        this.phoneNumber = userRegisterDTO.getPhoneNumber();
        this.address = userRegisterDTO.getAddress();
        this.hashedPassword = userRegisterDTO.getPassword();
        this.account = new AccountEntity(0.0);
        this.role = Role.USER;
        this.logout = new Date();
        this.pin = null;
    }

    public UUID getAccountNumber() {
        return account != null ? account.getId() : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
