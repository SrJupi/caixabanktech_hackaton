package com.hackathon.bankingapp.Entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "Accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double balance;

    public AccountEntity() {
    }

    public AccountEntity(double balance) {
        this.balance = balance;
    }

    public boolean modifyBalance(double value) {
        if (balance + value < 0.0) {
            return false;
        }
        balance += value;
        return true;
    }
}
