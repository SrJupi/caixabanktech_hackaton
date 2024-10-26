package com.hackathon.bankingapp.Controllers;

import com.hackathon.bankingapp.Services.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market/prices")
public class MarketController {
    @Autowired
    private MarketService marketService;
    @GetMapping("")
    public ResponseEntity<?> getAllPrices() {
        return marketService.getAllPrices();
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<?> getItemPrice(@PathVariable String symbol) {
        return marketService.getItemPrice(symbol);
    }
}
