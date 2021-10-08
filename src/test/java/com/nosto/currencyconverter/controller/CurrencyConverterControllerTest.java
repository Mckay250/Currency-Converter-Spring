package com.nosto.currencyconverter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nosto.currencyconverter.dtos.ServiceResponse;
import com.nosto.currencyconverter.dtos.requests.CurrencyConversionRequest;
import com.nosto.currencyconverter.dtos.responses.CurrencyConversionResponse;
import com.nosto.currencyconverter.services.CurrencyConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CurrencyConverterControllerTest {

    @Test
    void testConvertCurrency() {
        CurrencyConverterService currencyConverterService = mock(CurrencyConverterService.class);
        when(currencyConverterService.convertCurrency((CurrencyConversionRequest) any()))
                .thenReturn(new ServiceResponse<CurrencyConversionResponse>(1, "Not all who wander are lost"));
        CurrencyConverterController currencyConverterController = new CurrencyConverterController(currencyConverterService);
        ResponseEntity<ServiceResponse<CurrencyConversionResponse>> actualConvertCurrencyResult = currencyConverterController
                .convertCurrency(new CurrencyConversionRequest("GBP", "GBP", 10.0));
        assertEquals("<200 OK OK,ServiceResponse{code=1, message='Not all who wander are lost', object=null},[]>",
                actualConvertCurrencyResult.toString());
        assertTrue(actualConvertCurrencyResult.getHeaders().isEmpty());
        assertTrue(actualConvertCurrencyResult.hasBody());
        assertEquals(HttpStatus.OK, actualConvertCurrencyResult.getStatusCode());
        verify(currencyConverterService).convertCurrency((CurrencyConversionRequest) any());
    }
}

