package com.hackathon.bankingapp.DTO;

import com.hackathon.bankingapp.Entities.AccountEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountResponseDTO {
    private UUID    accountNumber;
    private double  balance;

    public AccountResponseDTO(AccountEntity account) {
        this.accountNumber = account.getId();
        this.balance = account.getBalance();
    }
}
