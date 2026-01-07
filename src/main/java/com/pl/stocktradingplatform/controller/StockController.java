package com.pl.stocktradingplatform.controller;

import com.pl.stocktradingplatform.entity.Stock;
import com.pl.stocktradingplatform.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService s) { this.stockService = s; }

    @GetMapping
    public List<Stock> all() { return stockService.getAllStocks(); }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> find(@PathVariable Long id) {
        Stock s = stockService.getStockById(id);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }
}

