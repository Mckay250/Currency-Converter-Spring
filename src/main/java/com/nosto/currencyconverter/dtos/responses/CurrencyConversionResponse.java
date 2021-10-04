package com.nosto.currencyconverter.dtos.responses;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import lombok.Data;

@Data
public class CurrencyConversionResponse {

    private CurrencySymbol baseCurrencySymbol;
    private CurrencySymbol outputCurrencySymbol;
    private double inputAmount;
    private double outputAmount;

}