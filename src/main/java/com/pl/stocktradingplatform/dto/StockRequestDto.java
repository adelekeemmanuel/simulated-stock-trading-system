package com.pl.stocktradingplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class StockRequestDto {
    @NotBlank
    private String symbol;
    @NotBlank
    private String name;
    @NotNull
    private Double currentPrice;
}
