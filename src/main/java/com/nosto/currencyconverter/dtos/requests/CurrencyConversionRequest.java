package com.nosto.currencyconverter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionRequest {

    @NotBlank(message = "base currency symbol is required")
    private String baseCurrencySymbol;
    @NotBlank(message = "output currency symbol is required")
    private String outputCurrencySymbol;
    @Min(1)
    private double amount;

}
