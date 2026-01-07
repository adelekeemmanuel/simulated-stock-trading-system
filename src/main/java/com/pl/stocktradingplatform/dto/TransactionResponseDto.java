package com.pl.stocktradingplatform.dto;

import java.time.LocalDateTime;

public class TransactionResponseDto {
    private Long id;
    private Long userId;
    private Long stockId;
    private Integer quantity;
    private Double priceAtTrade;
    private String type;
    private LocalDateTime timestamp;
}
