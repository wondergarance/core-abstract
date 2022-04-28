package com.example.demo.core;

import com.example.demo.dto.in.Stock;

public interface StockCore {

    Stock.State getStockState();

    void updateStock(Stock stock);
}
