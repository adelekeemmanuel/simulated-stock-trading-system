package com.pl.stocktradingplatform.controller;

import com.pl.stocktradingplatform.entity.Portfolio;
import com.pl.stocktradingplatform.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService p) {
        this.portfolioService = p;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Portfolio> get(@PathVariable Long userId) {
        Portfolio pf = portfolioService.getPortfolioByUserId(userId);
        if (pf == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pf);
    }
}
