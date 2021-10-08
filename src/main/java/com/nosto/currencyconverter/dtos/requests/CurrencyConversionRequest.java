package com.nosto.currencyconverter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionRequest {

    @NotBlank
    private String baseCurrencySymbol;
    @NotBlank
    private String outputCurrencySymbol;
    @NotBlank
    private double amount;

}
