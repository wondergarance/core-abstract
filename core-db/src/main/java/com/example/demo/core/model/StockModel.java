package com.example.demo.core.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity(name = "StockModel")
@Table(name = "stock")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String shoes;

    @Column
    private int capacity;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShoesJson() {
        return shoes;
    }

    public List<ShoeModel> getShoes() throws JsonProcessingException {
        var shoes = objectMapper.readValue(this.shoes, ShoeModel[].class);
        return Arrays.asList(shoes);
    }

    public void setShoesJson(List<ShoeModel> shoeList) throws JsonProcessingException {
        this.shoes = objectMapper.writeValueAsString(shoeList);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
