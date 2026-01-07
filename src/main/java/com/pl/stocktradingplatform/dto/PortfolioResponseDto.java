package com.pl.stocktradingplatform.dto;

import java.util.List;

public class PortfolioResponseDto {
    private Long id;
    private Long userId;
    private Double totalValue;
    private List<PortfolioStockResponseDto> holdings;
}
