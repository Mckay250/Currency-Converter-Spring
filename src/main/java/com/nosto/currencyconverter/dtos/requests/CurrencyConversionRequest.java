package com.nosto.currencyconverter.dtos.requests;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class CurrencyConversionRequest {

    @NotNull
    private String baseCurrencySymbol;
    @NotNull
    private String outputCurrencySymbol;
    @NotNull
    private double amount;

}
