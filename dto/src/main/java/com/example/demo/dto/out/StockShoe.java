package com.example.demo.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockShoe {
    @JsonProperty("color")
    Color color;
    @JsonProperty("size")
    int size;
    @JsonProperty("quantity")
    int quantity;

    public StockShoe() {}

    public StockShoe(Color color, int size, int quantity) {
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public enum Color{

        BLACK,
        BLUE,
        ;

    }
}
