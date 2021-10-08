package com.nosto.currencyconverter.dtos.responses;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import lombok.Data;

@Data
public class CurrencyConversionResponse {

    private String baseCurrencySymbol;
    private String outputCurrencySymbol;
    private double inputAmount;
    private double outputAmount;

}
