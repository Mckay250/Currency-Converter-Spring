package com.nosto.currencyconverter.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {

    @Value("${exchange.rates.api.key}")
    public String exchangeRatesApiKey;

    public final String exchangeRatesApiBaseUrl = "https://api.exchangeratesapi.io/v1/";

}
