package com.nosto.currencyconverter.controller;

import static org.mockito.Mockito.when;

import com.nosto.currencyconverter.models.Currency;
import com.nosto.currencyconverter.services.CurrencyService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CurrencyController.class})
@ExtendWith(SpringExtension.class)
class CurrencyControllerTest {
    @Autowired
    private CurrencyController currencyController;

    @MockBean
    private CurrencyService currencyService;

    @Test
    void testFetchCurrencies() throws Exception {
        when(this.currencyService.getAllCurrencies()).thenReturn(new ArrayList<Currency>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/currencies");
        MockMvcBuilders.standaloneSetup(this.currencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"message\":\"Successful\",\"data\":[]}"));
    }
}

