package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.DTO.MsgDTO;
import com.hackathon.bankingapp.DTO.RequestTransaction;
import com.hackathon.bankingapp.DTO.UserResponseDTO;
import com.hackathon.bankingapp.Entities.AccountEntity;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final UsersService usersService;
    private final AccountRepository accountRepository;
    public ResponseEntity<?> deposit(RequestTransaction transaction, String token) {
        Optional<UserEntity> optionalUser = usersService.getUserByToken(token);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = optionalUser.get();
        //check for pin

        AccountEntity account = user.getAccount();
        if (!account.modifyBalance(transaction.getAmount())) {
            return ResponseEntity.badRequest().build();
        }
        //generate transaction
        accountRepository.save(account);
        return ResponseEntity.ok(new MsgDTO("Cash deposit successfully"));
    }

    public ResponseEntity<?> withdraw(RequestTransaction transaction, String token) {
        Optional<UserEntity> optionalUser = usersService.getUserByToken(token);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = optionalUser.get();
        //check for pin
        AccountEntity account = user.getAccount();
        if (!account.modifyBalance(-transaction.getAmount())) {
            return ResponseEntity.badRequest().build();
        }
        //generate transaction
        accountRepository.save(account);
        return ResponseEntity.ok(new MsgDTO("Cash withdraw successfully"));
    }
}
