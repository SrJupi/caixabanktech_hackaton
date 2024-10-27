package com.hackathon.bankingapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PinCreationDTO {
    private String pin;
    private String password;
}
