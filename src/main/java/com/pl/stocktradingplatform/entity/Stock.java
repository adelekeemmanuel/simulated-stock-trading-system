package com.pl.stocktradingplatform.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@lombok.Data

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private String name;
    private double currentPrice;
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<PortfolioStock> portfolioStocks;
}
