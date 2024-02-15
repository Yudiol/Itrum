package com.yudiol.itrum.repository;

import com.yudiol.itrum.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, String> {

    @Query(value = "SELECT amount FROM wallets WHERE wallet_id = uuid(?)", nativeQuery = true)
    Optional<Long> findAmountByWalletId(UUID walletId);

    @Modifying
    @Query(value = "UPDATE wallets SET amount = ?2 + amount WHERE wallet_id =  uuid(?1)"
            , nativeQuery = true)
    Integer updateAmount(UUID walletId, Long amount);
}
