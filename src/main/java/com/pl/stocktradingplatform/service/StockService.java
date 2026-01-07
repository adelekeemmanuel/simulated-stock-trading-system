package com.pl.stocktradingplatform.service;

import com.pl.stocktradingplatform.entity.Stock;
import com.pl.stocktradingplatform.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }
}
