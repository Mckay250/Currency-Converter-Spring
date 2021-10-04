package com.nosto.currencyconverter.services;

import com.nosto.currencyconverter.dtos.ServiceResponse;
import com.nosto.currencyconverter.dtos.requests.CurrencyConversionRequest;
import com.nosto.currencyconverter.dtos.responses.CurrencyConversionResponse;

public interface CurrencyConverterService {

    ServiceResponse<CurrencyConversionResponse> convertCurrency(CurrencyConversionRequest request);
}
