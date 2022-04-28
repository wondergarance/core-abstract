package com.example.demo.controller;

import com.example.demo.dto.in.Stock;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockFacade stockFacade;

    @GetMapping(path = "/state")
    public ResponseEntity<Stock.State> getStockState(@RequestHeader Integer version) {
        return ResponseEntity.ok(stockFacade.get(version).getStockState());
    }

    @PatchMapping(path = "/stock")
    public ResponseEntity<Void> updateStock(Stock stock, @RequestHeader Integer version) {
        // TODO: update DB

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
