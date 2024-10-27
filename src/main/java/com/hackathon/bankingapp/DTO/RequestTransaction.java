package com.hackathon.bankingapp.DTO;

import lombok.Getter;

@Getter
public class RequestTransaction {
    private final double amount;
    private final String pin;

    public RequestTransaction(double amount, String pin) {
        this.amount = amount;
        this.pin = pin;
    }
}
