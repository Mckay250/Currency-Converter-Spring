package com.nosto.currencyconverter.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {


    @Value("${EXCHANGE_RATES_API_KEY}")
    public String exchangeRatesApiKey;

    public final String exchangeRatesApiBaseUrl = "http://api.exchangeratesapi.io/v1/";

}
