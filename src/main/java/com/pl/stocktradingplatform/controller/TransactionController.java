package com.pl.stocktradingplatform.controller;

import com.pl.stocktradingplatform.entity.Transaction;
import com.pl.stocktradingplatform.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/transactions")
    public class TransactionController {

        private final TransactionService txService;

        public TransactionController(TransactionService txService) {
            this.txService = txService;
        }

        @GetMapping("/user/{userId}")
        public List<Transaction> userTx(@PathVariable Long userId) {
            return txService.getUserTx(userId);
        }

        @PostMapping
        public Transaction save(@RequestBody Transaction tx) {
            return txService.saveTx(tx);
        }

        @PostMapping("/buy")
        public ResponseEntity<String> buy(
                @RequestParam Long userId,
                @RequestParam Long stockId,
                @RequestParam int quantity) {
            return ResponseEntity.ok(txService.buyStock(userId, stockId, quantity));
        }

        @PostMapping("/sell")
        public ResponseEntity<String> sell(
                @RequestParam Long userId,
                @RequestParam Long stockId,
                @RequestParam int quantity) {
            return ResponseEntity.ok(txService.sellStock(userId, stockId, quantity));
        }
    }
