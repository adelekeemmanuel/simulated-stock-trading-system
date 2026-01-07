package com.pl.stocktradingplatform.dto;

import jakarta.validation.constraints.NotNull;

public class TransactionRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private Long stockId;

    @NotNull
    private Integer quantity;

    @NotNull
    private String type;  // "BUY" or "SELL"
}
