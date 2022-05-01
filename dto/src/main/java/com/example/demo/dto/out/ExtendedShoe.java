package com.example.demo.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtendedShoe {
    @JsonProperty("color")
    Color color;
    @JsonProperty("size")
    int size;
    @JsonProperty("quantity")
    int quantity;

    public ExtendedShoe() {}

    public ExtendedShoe(Color color, int size, int quantity) {
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
