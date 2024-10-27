package com.hackathon.bankingapp.Services;

import com.hackathon.bankingapp.DTO.MsgDTO;
import com.hackathon.bankingapp.DTO.PinCreationDTO;
import com.hackathon.bankingapp.DTO.PinUpdateDTO;
import com.hackathon.bankingapp.DTO.RequestTransaction;
import com.hackathon.bankingapp.Entities.AccountEntity;
import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if (!user.getPin().equals(transaction.getPin())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid PIN");
        }

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
        if (!user.getPin().equals(transaction.getPin())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid PIN");
        }
        AccountEntity account = user.getAccount();
        if (!account.modifyBalance(-transaction.getAmount())) {
            return ResponseEntity.badRequest().build();
        }
        //generate transaction
        accountRepository.save(account);
        return ResponseEntity.ok(new MsgDTO("Cash withdraw successfully"));
    }

    boolean validatePin(String pin) {
        return pin != null && pin.length() == 4 && pin.matches("\\d{4}");
    }
    public ResponseEntity<?> createPin(PinCreationDTO pin, String token) {
        UserEntity user = usersService.getUserByToken(token).get();
        if (!usersService.comparePasswords(user.getHashedPassword(), pin.getPassword()) ||
                user.getPin() != null ||
                !validatePin(pin.getPin())) {
            return ResponseEntity.badRequest().body("Bad credentials");
        }
        user.setPin(pin.getPin());
        usersService.updateUser(user);
        return ResponseEntity.ok(new MsgDTO("PIN created successfully"));
    }

    public ResponseEntity<?> updatePin(PinUpdateDTO pin, String token) {
        UserEntity user = usersService.getUserByToken(token).get();
        if (!usersService.comparePasswords(user.getHashedPassword(), pin.getPassword()) ||
                user.getPin() == null ||
                !validatePin(pin.getNewPin()) ||
                !pin.getOldPin().equals(user.getPin())
        ) {
            return ResponseEntity.badRequest().body("Bad credentials");
        }
        user.setPin(pin.getNewPin());
        usersService.updateUser(user);
        return ResponseEntity.ok(new MsgDTO("PIN updated successfully"));
    }

}
