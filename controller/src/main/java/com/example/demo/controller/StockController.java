package com.example.demo.controller;

import com.example.demo.dto.out.ExtendedShoe;
import com.example.demo.dto.out.Stock;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockFacade stockFacade;

    @GetMapping(path = "/info")
    public ResponseEntity<Stock> getStock(@RequestParam String name, @RequestHeader Integer version) {
        return ResponseEntity.ok(stockFacade.get(version).getStock(name));
    }

    @GetMapping(path = "/state")
    public ResponseEntity<Stock.State> getStockState(@RequestParam String name, @RequestHeader Integer version) {
        return ResponseEntity.ok(stockFacade.get(version).getStockState(name));
    }

    @PatchMapping(path = "/stock", consumes = { "application/json" })
    public ResponseEntity<Stock> updateStock(@RequestParam String name,
                                            @RequestBody List<ExtendedShoe> shoes,
                                            @Valid @RequestHeader Integer version) {
        return ResponseEntity.ok(stockFacade.get(version).updateStock(name, shoes));
    }
}
