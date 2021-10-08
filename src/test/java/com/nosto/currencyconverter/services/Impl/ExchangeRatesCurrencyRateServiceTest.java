package com.nosto.currencyconverter.services.Impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nosto.currencyconverter.dtos.responses.ExchangeRatesCurrencyRateResponse;
import com.nosto.currencyconverter.exceptions.ConverterServiceException;
import com.nosto.currencyconverter.utils.ApplicationConstants;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ContextConfiguration(classes = {ExchangeRatesCurrencyRateService.class, ApplicationConstants.class,
        RestTemplate.class})
@ExtendWith(SpringExtension.class)
class ExchangeRatesCurrencyRateServiceTest {

    @MockBean
    private ApplicationConstants applicationConstants;

    @Autowired
    private ExchangeRatesCurrencyRateService exchangeRatesCurrencyRateService;

    @MockBean
    private RestTemplate restTemplate;


    @Test
    void testGetCurrencyRate_whenNullException() throws RestClientException {
        when(this.restTemplate.getForObject((String) any(), (Class<Object>) any(), (Object[]) any()))
                .thenThrow(new NullPointerException("foo"));
        assertThrows(ConverterServiceException.class,
                () -> this.exchangeRatesCurrencyRateService.getCurrencyRate("GBP", "GBP"));
        verify(this.restTemplate).getForObject((String) any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testGetCurrencyRate() throws RestClientException {
        ExchangeRatesCurrencyRateResponse exchangeRatesCurrencyRateResponse = mock(ExchangeRatesCurrencyRateResponse.class);
        when(exchangeRatesCurrencyRateResponse.getRates()).thenReturn(new HashMap<String, Double>(1));
        when(exchangeRatesCurrencyRateResponse.getBase()).thenReturn("GBP");
        when(exchangeRatesCurrencyRateResponse.isSuccess()).thenReturn(true);
        when(this.restTemplate.getForObject((String) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(exchangeRatesCurrencyRateResponse);
        assertThrows(ConverterServiceException.class,
                () -> this.exchangeRatesCurrencyRateService.getCurrencyRate("GBP", "NGN"));
        verify(this.restTemplate).getForObject((String) any(), (Class<Object>) any(), (Object[]) any());
        verify(exchangeRatesCurrencyRateResponse).getBase();
        verify(exchangeRatesCurrencyRateResponse).getRates();
        verify(exchangeRatesCurrencyRateResponse).isSuccess();
    }
}

