package com.nosto.currencyconverter.services;

import com.nosto.currencyconverter.models.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
}
