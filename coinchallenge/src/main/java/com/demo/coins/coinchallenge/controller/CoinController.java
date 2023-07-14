package com.demo.coins.coinchallenge.controller;

import com.demo.coins.coinchallenge.exception.InsufficientCoinsException;
import com.demo.coins.coinchallenge.model.ChangeRequest;
import com.demo.coins.coinchallenge.model.ChangeResponse;
import com.demo.coins.coinchallenge.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping("/change")
    public ResponseEntity<ChangeResponse> getChange(@RequestBody ChangeRequest request) {
        try {
            ChangeResponse response = coinService.getChange(request.getAmount());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InsufficientCoinsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
