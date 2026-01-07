package com.pl.stocktradingplatform.repository;

import com.pl.stocktradingplatform.entity.PortfolioStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PortfolioStockRepository extends JpaRepository<PortfolioStock, Long> {
    List<PortfolioStock> findByPortfolioId(Long portfolioId);
}
