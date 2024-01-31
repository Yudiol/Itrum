package com.yudiol.itrum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yudiol.itrum.model.OperationType;
import com.yudiol.itrum.model.Wallet;
import com.yudiol.itrum.service.WalletService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class WalletControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    UUID walletId = UUID.randomUUID();

    @Test
    @SneakyThrows
    void getAmount() {
        mockMvc.perform(get("/api/v1/wallets/{walletId}", walletId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(walletService).viewAccountBalance(walletId);
    }

    @Test
    @SneakyThrows
    void changeBalance() {

        Wallet wallet = new Wallet(walletId, OperationType.DEPOSIT, 100L);

        String result = mockMvc.perform(patch("/api/v1/wallets")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wallet)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(result, "Баланс был успешно изменён");
    }
}