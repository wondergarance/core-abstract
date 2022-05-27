package com.example.demo.controller;

import com.example.demo.core.service.StockService;
import com.example.demo.dto.out.StockShoe;
import com.example.demo.dto.out.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;

import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StockControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StockService stockService;

    private Stock stock;
    private final String name = "test";

    @Before
    public void setUp() {
        StockShoe shoe = new StockShoe(StockShoe.Color.BLACK, 41, 0);
        this.stock = Stock.builder()
                .name(name)
                .state(Stock.State.SOME)
                .shoes(Arrays.asList(shoe))
                .build();
    }

    @Test
    public void getStockByName_thenReturnJson() throws Exception {
        given(stockService.getStock(Mockito.any())).willReturn(stock);

        mvc.perform(get("/stock?name=" + name)
                .header("version", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.shoes", hasSize(1)));

        verify(stockService, VerificationModeFactory.times(1)).getStock(Mockito.any());
        reset(stockService);
    }

    @Test
    public void getStockState_thenReturnState() throws Exception {
        given(stockService.getState(Mockito.any())).willReturn(Stock.State.SOME);

        mvc.perform(get("/stock/state?name=" + name)
                        .header("version", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Stock.State.SOME.name())));

        verify(stockService, VerificationModeFactory.times(1)).getState(Mockito.any());
        reset(stockService);
    }

    @Test
    public void updateStock_thenReturnJson() throws Exception {
        given(stockService.updateStock(Mockito.any(), Mockito.any())).willReturn(stock);
        var content = "[{\"color\": \"BLACK\",\"size\": 41,\"quantity\": 0}]";

        mvc.perform(patch("/stock?name=" + name)
                        .header("version", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.shoes", hasSize(1)))
                .andExpect(jsonPath("$.shoes[0].quantity", is(0)));

        verify(stockService, VerificationModeFactory.times(1))
                .updateStock(Mockito.any(), Mockito.any());
        reset(stockService);
    }
}
