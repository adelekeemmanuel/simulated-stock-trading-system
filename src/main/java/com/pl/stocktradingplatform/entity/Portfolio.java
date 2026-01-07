package com.pl.stocktradingplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Double totalValue;
    
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioStock> portfolioStocks;

}
