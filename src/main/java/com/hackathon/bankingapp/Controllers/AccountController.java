package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.Services.AccountsService;
import com.hackathon.bankingapp.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountsService accountsService;

/*    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(){
        return dashboardService.getUserInfo();
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccountInfo(){
        return dashboardService.getAccountInfo();
    }*/
}
