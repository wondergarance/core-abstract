package com.example.demo.core.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class StockModel {
    private List<ShoeModel> shoes;
    private int capacity;

    public List<ShoeModel> getShoes() {
        return shoes;
    }

    public void setShoes(List<ShoeModel> shoes) {
        this.shoes = shoes;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
