package com.example.demo.dto.out;

import com.example.demo.dto.out.ExtendedShoe;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Builder
@JsonDeserialize(builder = Stock.StockBuilder.class)
public class Stock {

    String name;
    State state;
    List<ExtendedShoe> shoes;

    public enum State {
        EMPTY,
        FULL,
        SOME
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockBuilder {
    }
}
