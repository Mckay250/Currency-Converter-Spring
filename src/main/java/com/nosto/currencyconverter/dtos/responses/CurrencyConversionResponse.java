package com.nosto.currencyconverter.dtos.responses;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionResponse {

    private String baseCurrencySymbol;
    private String outputCurrencySymbol;
    private double inputAmount;
    private double outputAmount;

}
