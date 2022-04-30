package com.example.demo.core.service;

import com.example.demo.core.model.ShoeModel;
import com.example.demo.core.model.StockModel;
import com.example.demo.core.repository.StockRepository;
import com.example.demo.dto.out.ExtendedShoe;
import com.example.demo.dto.out.Stock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Stock getStock(String name) {
        var stockModel = stockRepository.findByName(name).orElse(null);
        return mapToStock(stockModel);
    }

    public Stock.State getState(String name) {
        return getStock(name).getState();
    }

    public Stock updateStock(String name, List<ExtendedShoe> shoes) {
        var stockModel = stockRepository.findByName(name).orElse(null);
        if (stockModel != null) {
            updateStockModel(shoes, stockModel);
            stockRepository.save(stockModel);
        }

        return getStock(name);
    }

    private Stock mapToStock(StockModel stockModel) {
        if (stockModel != null) {
            try {
                var shoes = convertToShoeList(stockModel.getShoes());

                return Stock.builder()
                        .name(stockModel.getName())
                        .shoes(shoes)
                        .state(getState(shoes))
                        .build();
            } catch (JsonProcessingException e) {

            }
        }
        return null;
    }

    private List<ExtendedShoe> convertToShoeList(String shoesStr) throws JsonProcessingException {
        var shoes = objectMapper.readValue(shoesStr, ExtendedShoe[].class);
        return Arrays.asList(shoes);
    }

    private String convertToShoesJsonStr(List<ExtendedShoe> shoeList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(shoeList);
    }


    private int getCapacity(List<ExtendedShoe> shoes) {
        return shoes.stream()
                .map(ExtendedShoe::getQuantity)
                .reduce(0, Integer::sum);
    }

    private Stock.State getState(List<ExtendedShoe> shoes) {
        var count = getCapacity(shoes);
        if (count == 0) {
            return Stock.State.EMPTY;
        } else if (count == 30) {
            return Stock.State.FULL;
        }
        return Stock.State.SOME;
    }

    private void updateStockModel(List<ExtendedShoe> shoes, StockModel stockModel) {
        try {
            List<ExtendedShoe> dbShoes = convertToShoeList(stockModel.getShoes());
            for (ExtendedShoe shoe : shoes) {
                ExtendedShoe dbShoe = getShoeByfields(dbShoes, shoe.getColor(), shoe.getSize());
                if (dbShoe != null) {
                    int quantity = shoe.getQuantity();
                    // TODO validate max capacity
                    if (quantity > 30) {
                        throw new IllegalArgumentException("Shoe's quantity exceeds 30!");
                    }
                    dbShoe.setQuantity(quantity);
                }
            }

            var dbShoesStr = convertToShoesJsonStr(dbShoes);
            var capacity = getCapacity(dbShoes);
            if (capacity > 30) {
                throw new IllegalArgumentException("Stock capacity exceeds 30!");
            }

            stockModel.setShoes(dbShoesStr);
            stockModel.setCapacity(capacity);
        } catch (JsonProcessingException e) {

        }
    }

    private ExtendedShoe getShoeByfields(List<ExtendedShoe> dbShoes, ExtendedShoe.Color color, int size) {
        for (ExtendedShoe shoe : dbShoes) {
            if (shoe.getColor() == color && shoe.getSize() == size) {
                return shoe;
            }
        }
        return null;
    }
}
