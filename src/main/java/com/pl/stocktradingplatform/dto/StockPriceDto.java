package com.pl.stocktradingplatform.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPriceDto {
    private String symbol;
    private String date;
    private Double closePrice;

    // Getters & setters
}
