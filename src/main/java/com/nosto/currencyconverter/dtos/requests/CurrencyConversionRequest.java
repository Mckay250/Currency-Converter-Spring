package com.nosto.currencyconverter.dtos.requests;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class CurrencyConversionRequest {

    @NotNull
    private CurrencySymbol baseCurrencySymbol;
    @NotNull
    private CurrencySymbol outputCurrencySymbol;
    @NotNull
    private double amount;

}
