package com.yudiol.itrum.service.Impl;

import com.yudiol.itrum.exception.errors.BadRequestError;
import com.yudiol.itrum.exception.errors.NotFoundException;
import com.yudiol.itrum.model.Wallet;
import com.yudiol.itrum.repository.WalletRepository;
import com.yudiol.itrum.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    public Long viewAccountBalance(UUID walletId) {
        return repository.findAmountByWalletId(walletId)
                .orElseThrow(() -> new NotFoundException(" wallet ", walletId.toString()));
    }

    @Transactional
    public void changeBalance(Wallet walletDto) {
        Wallet wallet = repository.findByWalletId(walletDto.getWalletId())
                .orElseThrow(() -> new NotFoundException(" wallet ", walletDto.getWalletId().toString()));
        wallet.setOperationType(walletDto.getOperationType());
        long balance = wallet.getAmount() + walletDto.getAmount();
        if (balance < 0) {
            throw new BadRequestError("На счету не достаточно средств");
        }
        wallet.setAmount(balance);
        repository.save(wallet);
    }

}
