package com.yudiol.itrum.service;

import com.yudiol.itrum.dto.RequestWallet;

import java.util.UUID;

public interface WalletService {
    Long viewAccountBalance(UUID walletId);

    void changeBalance(RequestWallet walletDto);
}
