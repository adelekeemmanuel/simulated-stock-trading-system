package com.pl.stocktradingplatform.repository;

import com.pl.stocktradingplatform.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserId(Long id);
}
