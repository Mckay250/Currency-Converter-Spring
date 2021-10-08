package com.nosto.currencyconverter.services;

import com.nosto.currencyconverter.dtos.responses.CurrencyRateResponse;
import com.nosto.currencyconverter.enums.CurrencySymbol;

public interface CurrencyRateService {

    CurrencyRateResponse getCurrencyRate(String baseCurrency, String outputCurrency);

}
