package com.yudiol.itrum.service;

import com.yudiol.itrum.model.Wallet;

import java.util.UUID;

public interface WalletService {
    Long viewAccountBalance(UUID walletId);

    void changeBalance(Wallet walletDto);
}
