package com.pl.stocktradingplatform.service;

import com.pl.stocktradingplatform.entity.*;
import com.pl.stocktradingplatform.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository txRepo;
    private final UserService userService;
    private final StockService stockService;
    private final PortfolioService portfolioService;
    private final PortfolioStockService psService;

    public TransactionService(TransactionRepository txRepo,
                              UserService userService,
                              StockService stockService,
                              PortfolioService portfolioService,
                              PortfolioStockService psService) {
        this.txRepo = txRepo;
        this.userService = userService;
        this.stockService = stockService;
        this.portfolioService = portfolioService;
        this.psService = psService;
    }

    public List<Transaction> getUserTx(Long userId) {
        return txRepo.findByUserId(userId);
    }

    public Transaction saveTx(Transaction tx) {
        return txRepo.save(tx);
    }

    public String buyStock(Long userId, Long stockId, int qty) {
        User u = userService.getUserById(userId);
        Stock s = stockService.getStockById(stockId);

        if (u == null) return "User not found";
        if (s == null) return "Stock not found";

        double cost = s.getCurrentPrice() * qty;
        if (u.getBalance() < cost) return "Insufficient balance";

        u.setBalance(u.getBalance() - cost);
        userService.createUser(u);

        Portfolio pf = portfolioService.getPortfolioByUserId(userId);
        if (pf == null) {
            pf = new Portfolio();
            pf.setUser(u);
            pf.setTotalValue(0.0);
            pf.setPortfolioStocks(new ArrayList<>());
        }

        PortfolioStock existing = null;
        if (pf.getPortfolioStocks() != null) {
            for (PortfolioStock ps : pf.getPortfolioStocks()) {
                if (ps.getStock().getId().equals(stockId)) existing = ps;
            }
        }

        if (existing == null) {
            PortfolioStock ps = new PortfolioStock();
            ps.setPortfolio(pf);
            ps.setStock(s);
            ps.setQuantity(qty);
            ps.setAverageBuyPrice(s.getCurrentPrice());
            pf.getPortfolioStocks().add(ps);
            psService.save(ps);
        } else {
            int newQty = existing.getQuantity() + qty;
            double newAvg = ((existing.getAverageBuyPrice() * existing.getQuantity()) + cost) / newQty;
            existing.setQuantity(newQty);
            existing.setAverageBuyPrice(newAvg);
            psService.save(existing);
        }

        pf.setTotalValue(pf.getTotalValue() + cost);
        portfolioService.savePortfolio(pf);

        Transaction tx = new Transaction();
        tx.setUser(u);
        tx.setStock(s);
        tx.setQuantity(qty);
        tx.setPriceAtTrade(s.getCurrentPrice());
        tx.setType(TransactionType.BUY);
        txRepo.save(tx);

        return "Bought " + qty + " of " + s.getSymbol();
    }

    public String sellStock(Long userId, Long stockId, int qty) {
        User u = userService.getUserById(userId);
        Stock s = stockService.getStockById(stockId);

        if (u == null) return "User not found";
        if (s == null) return "Stock not found";

        Portfolio pf = portfolioService.getPortfolioByUserId(userId);
        if (pf == null) return "No portfolio";

        PortfolioStock found = null;
        if (pf.getPortfolioStocks() != null) {
            for (PortfolioStock ps : pf.getPortfolioStocks()) {
                if (ps.getStock().getId().equals(stockId)) found = ps;
            }
        }
        if (found == null || found.getQuantity() < qty) return "Not enough shares";

        double proceeds = s.getCurrentPrice() * qty;
        u.setBalance(u.getBalance() + proceeds);
        userService.createUser(u);

        int remaining = found.getQuantity() - qty;
        if (remaining == 0) {
            pf.getPortfolioStocks().remove(found);
            psService.delete(found);
        } else {
            found.setQuantity(remaining);
            psService.save(found);
        }
        pf.setTotalValue(pf.getTotalValue() - proceeds);
        portfolioService.savePortfolio(pf);

        Transaction tx = new Transaction();
        tx.setUser(u);
        tx.setStock(s);
        tx.setQuantity(qty);
        tx.setPriceAtTrade(s.getCurrentPrice());
        tx.setType(TransactionType.SELL);
        txRepo.save(tx);

        return "Sold " + qty + " of " + s.getSymbol();
    }
}
