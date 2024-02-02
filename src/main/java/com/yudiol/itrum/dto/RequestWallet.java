package com.yudiol.itrum.dto;

import com.yudiol.itrum.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestWallet {

    private UUID walletId;

    private OperationType operationType;

    private Long amount;
}
