package com.hackathon.bankingapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PinUpdateDTO {
    String oldPin;
    String password;
    String newPin;
}
