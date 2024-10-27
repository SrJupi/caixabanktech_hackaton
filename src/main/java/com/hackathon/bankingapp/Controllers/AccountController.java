package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.DTO.PinCreationDTO;
import com.hackathon.bankingapp.DTO.PinUpdateDTO;
import com.hackathon.bankingapp.DTO.RequestTransaction;
import com.hackathon.bankingapp.Services.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountsService accountsService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(
            @RequestBody RequestTransaction transaction,
            @RequestHeader("Authorization") String auth
    ){
        return accountsService.deposit(transaction, auth.substring(7));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(
            @RequestHeader("Authorization") String auth,
            @RequestBody RequestTransaction transaction
    ){
        return accountsService.withdraw(transaction, auth.substring(7));
    }

    @PostMapping("/pin/create")
    public ResponseEntity<?> createPin(
            @RequestHeader("Authorization") String auth,
            @RequestBody PinCreationDTO pin
    ) {
        return accountsService.createPin(pin, auth.substring(7));
    }

    @PostMapping("/pin/update")
    public ResponseEntity<?> createPin(
            @RequestHeader("Authorization") String auth,
            @RequestBody PinUpdateDTO pin
    ) {
        return accountsService.updatePin(pin, auth.substring(7));
    }
}


/*
Recordatorio de endpoints que faltan en el README:
/api/account/pin/create -> POST
/api/account/pin/update -> POST
/api/account/assets -> GET -> Devuelve los assets que posee un usuario en su cuenta
/api/account/net-worth -> GET -> Devuelve el patrimonio neto de una cuenta
 */