package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.DTO.RequestTransaction;
import com.hackathon.bankingapp.Services.AccountsService;
import com.hackathon.bankingapp.Services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
