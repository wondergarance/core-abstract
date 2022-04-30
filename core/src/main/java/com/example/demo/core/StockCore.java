package com.example.demo.core;

import com.example.demo.dto.out.ExtendedShoe;
import com.example.demo.dto.out.Stock;

import java.util.List;

public interface StockCore {

    Stock.State getStockState(String name);

    Stock getStock(String name);

    Stock updateStock(String name, List<ExtendedShoe> shoes);
}
