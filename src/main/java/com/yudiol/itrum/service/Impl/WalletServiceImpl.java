package com.yudiol.itrum.service.Impl;

import com.yudiol.itrum.dto.RequestWallet;
import com.yudiol.itrum.exception.errors.BadRequestError;
import com.yudiol.itrum.exception.errors.NotFoundException;
import com.yudiol.itrum.model.OperationType;
import com.yudiol.itrum.repository.WalletRepository;
import com.yudiol.itrum.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    @Transactional(readOnly = true)
    public Long viewAccountBalance(UUID walletId) {
        return repository.findAmountByWalletId(walletId)
                .orElseThrow(() -> new NotFoundException(" wallet ", walletId.toString()));
    }

    @Transactional
    public void changeBalance(RequestWallet wallet) {

        if (wallet.getOperationType() == OperationType.WITHDRAW) {
            wallet.setAmount(-wallet.getAmount());
        }

        try {
            int updatedRows = repository.updateAmount(wallet.getWalletId(), wallet.getAmount());
            if (updatedRows == 0) {
                throw new NotFoundException("Wallet ", wallet.getWalletId().toString());
            }
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestError("На счету не достаточно средств");
        }
    }

}
