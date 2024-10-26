package com.hackathon.bankingapp.Repositories;

import com.hackathon.bankingapp.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    UserEntity findByEmail(String email);
    UserEntity findByAccountNumber(UUID accountNumber);

}

