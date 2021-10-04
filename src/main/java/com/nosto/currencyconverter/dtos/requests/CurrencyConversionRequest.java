package com.nosto.currencyconverter.dtos.requests;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import lombok.Data;

@Data
public class CurrencyConversionRequest {

    private CurrencySymbol baseCurrencySymbol;
    private CurrencySymbol outputCurrencySymbol;
    private double amount;

}
