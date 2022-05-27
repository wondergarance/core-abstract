package com.example.demo.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonDeserialize(builder = Stock.StockBuilder.class)
public class Stock {

    String name;
    State state;
    List<StockShoe> shoes;

    public enum State {
        EMPTY,
        FULL,
        SOME
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockBuilder {
    }
}
