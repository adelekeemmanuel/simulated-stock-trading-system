package com.pl.stocktradingplatform.service;

import com.pl.stocktradingplatform.entity.PortfolioStock;
import com.pl.stocktradingplatform.repository.PortfolioStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioStockService {

    private final PortfolioStockRepository portfolioStockRepository;

    public PortfolioStockService(PortfolioStockRepository portfolioStockRepository) {
        this.portfolioStockRepository = portfolioStockRepository;
    }

    public List<PortfolioStock> getHoldingsByPortfolioId(Long portfolioId) {
        return portfolioStockRepository.findByPortfolioId(portfolioId);
    }

    // This method matches what TransactionService calls
    public PortfolioStock save(PortfolioStock portfolioStock) {
        return portfolioStockRepository.save(portfolioStock);
    }

    // This method also matches what TransactionService calls
    public void delete(PortfolioStock portfolioStock) {
        portfolioStockRepository.delete(portfolioStock);
    }
}
