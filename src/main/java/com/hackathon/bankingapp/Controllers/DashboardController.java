package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.Entities.UserEntity;
import com.hackathon.bankingapp.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authorization){
        return dashboardService.getUserInfo(authorization.substring(7));
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccountInfo(@RequestHeader("Authorization") String authorization){
        return dashboardService.getAccountInfo(authorization.substring(7));
    }
}
