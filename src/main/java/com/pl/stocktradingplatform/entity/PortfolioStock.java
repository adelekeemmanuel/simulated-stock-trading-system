package com.pl.stocktradingplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "portfolio_stocks")
public class PortfolioStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Integer quantity;
    private Double averageBuyPrice;
}
