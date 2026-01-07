package com.pl.stocktradingplatform.service;

import com.pl.stocktradingplatform.entity.Portfolio;
import com.pl.stocktradingplatform.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio getPortfolioByUserId(Long userId) {
        return portfolioRepository.findByUserId(userId).orElse(null);
    }

    public Portfolio savePortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }
}
