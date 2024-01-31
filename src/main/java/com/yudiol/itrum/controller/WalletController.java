package com.yudiol.itrum.controller;

import com.yudiol.itrum.dto.ResponseAmount;
import com.yudiol.itrum.model.Wallet;
import com.yudiol.itrum.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class WalletController {

    private final WalletService service;

    @GetMapping("/{walletId}")
    public ResponseAmount getAmount(@PathVariable("walletId") UUID walletId) {
        return new ResponseAmount(service.viewAccountBalance(walletId));
    }

    @PatchMapping()
    public ResponseEntity<String> changeBalance(@RequestBody @Valid Wallet wallet) {
        service.changeBalance(wallet);
        return ResponseEntity.ok("Баланс был успешно изменён");
    }
}
