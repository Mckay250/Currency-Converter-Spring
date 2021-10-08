package com.nosto.currencyconverter.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CurrencyConversionRequest {

    @NotBlank
    private String baseCurrencySymbol;
    @NotBlank
    private String outputCurrencySymbol;
    @NotBlank
    private double amount;

}
