package com.nosto.currencyconverter.services.Impl;

import com.nosto.currencyconverter.dtos.responses.CurrencyConversionResponse;
import com.nosto.currencyconverter.dtos.responses.CurrencyRateResponse;
import com.nosto.currencyconverter.dtos.responses.ExchangeRatesCurrencyRateResponse;
import com.nosto.currencyconverter.enums.CurrencySymbol;
import com.nosto.currencyconverter.exceptions.ConverterServiceException;
import com.nosto.currencyconverter.services.CurrencyConverterService;
import com.nosto.currencyconverter.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRatesCurrencyConverterService implements CurrencyConverterService {


    private final ApplicationConstants applicationConstants;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ExchangeRatesCurrencyConverterService(ApplicationConstants applicationConstants, WebClient.Builder webClientBuilder) {
        this.applicationConstants = applicationConstants;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public CurrencyRateResponse getCurrencyRate(CurrencySymbol baseCurrency, CurrencySymbol outputCurrency) {
        try {
            ExchangeRatesCurrencyRateResponse exchangeRatesCurrencyRateResponse = webClientBuilder.build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(applicationConstants.exchangeRatesApiBaseUrl + "latest")
                            .queryParam("access_key", applicationConstants.exchangeRatesApiKey)
                            .queryParam("base", baseCurrency)
                            .queryParam("symbols", outputCurrency)
                            .build()
                    ).retrieve()
                    .bodyToMono(ExchangeRatesCurrencyRateResponse.class).block();

            if (exchangeRatesCurrencyRateResponse == null) {
                throw new NullPointerException("Response is null");
            }
            if (!exchangeRatesCurrencyRateResponse.isSuccess()) {
                throw new ConverterServiceException(exchangeRatesCurrencyRateResponse.getError().getInfo(),
                        exchangeRatesCurrencyRateResponse.getError().getCode());
            }

            CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
            currencyRateResponse.setBaseCurrencySymbol(CurrencySymbol.valueOf(exchangeRatesCurrencyRateResponse.getBase()));
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


