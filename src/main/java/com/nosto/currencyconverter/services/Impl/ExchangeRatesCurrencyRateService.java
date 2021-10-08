package com.nosto.currencyconverter.services.Impl;

import com.nosto.currencyconverter.dtos.responses.CurrencyRateResponse;
import com.nosto.currencyconverter.dtos.responses.ExchangeRatesCurrencyRateResponse;
import com.nosto.currencyconverter.exceptions.ConverterServiceException;
import com.nosto.currencyconverter.services.CurrencyRateService;
import com.nosto.currencyconverter.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExchangeRatesCurrencyRateService implements CurrencyRateService {

    @Autowired
    private ApplicationConstants applicationConstants;
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    public ExchangeRatesCurrencyRateService(ApplicationConstants applicationConstants, WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
//        this.applicationConstants = applicationConstants;
//        this.restTemplate = restTemplate;
//    }

    @Override
    public CurrencyRateResponse getCurrencyRate(String baseCurrency, String outputCurrency) {
        try {

            String url = UriComponentsBuilder
                    .fromUriString(applicationConstants.exchangeRatesApiBaseUrl + "/latest")
                    .queryParam("access_key", applicationConstants.exchangeRatesApiKey)
                    .queryParam("base", baseCurrency)
                    .queryParam("symbols", outputCurrency)
                    .build().toUriString();
            ExchangeRatesCurrencyRateResponse exchangeRatesCurrencyRateResponse =
                    this.restTemplate.getForObject(url, ExchangeRatesCurrencyRateResponse.class);
            if (exchangeRatesCurrencyRateResponse == null) {
                throw new NullPointerException("Response is null");
            }
            if (!exchangeRatesCurrencyRateResponse.isSuccess()) {
                throw new ConverterServiceException(exchangeRatesCurrencyRateResponse.getError().getInfo(),
                        exchangeRatesCurrencyRateResponse.getError().getCode());
            }

            CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
            currencyRateResponse.setBaseCurrencySymbol(exchangeRatesCurrencyRateResponse.getBase());
            currencyRateResponse.setOutputCurrencySymbol(outputCurrency);
            currencyRateResponse.setRate(exchangeRatesCurrencyRateResponse.getRates().get(outputCurrency.toString()));
            return currencyRateResponse;

        } catch (WebClientResponseException we) {
            throw new ConverterServiceException(we.getMessage(), we.getRawStatusCode());
        } catch (NullPointerException ne) {
            throw new ConverterServiceException(ne.getMessage(), 0);
        }
    }
}


