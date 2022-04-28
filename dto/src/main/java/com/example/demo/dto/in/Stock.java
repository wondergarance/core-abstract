package com.example.demo.dto.in;

import com.example.demo.dto.out.ExtendedShoe;

import java.util.List;

public class Stock {

    State state;
    List<ExtendedShoe> shoes;

    public enum State {
        EMPTY,
        FULL,
        SOME
    }
}
