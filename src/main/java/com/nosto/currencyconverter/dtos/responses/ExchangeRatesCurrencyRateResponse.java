package com.nosto.currencyconverter.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nosto.currencyconverter.enums.CurrencySymbol;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesCurrencyRateResponse {

    private boolean success;
    private String timestamp;
    private String base;
    private String date;
    private HashMap<String, Double> rates;
    private ErrorResponse error;


    @Data
    public static class ErrorResponse {
        private int code;
        private String info;
    }
//            obj = (HashMap<String, String>) response.getObject();
//    {
//        "success": true,
//            "timestamp": 1519296206,
//            "base": "USD",
//            "date": "2021-03-17",
//            "rates": {
//        "GBP": 0.72007,
//                "JPY": 107.346001,
//                "EUR": 0.813399,
//    }
//    }"success": false,
//  "error": {
//    "code": 104,
//    "info": "Your monthly API request volume has been reached. Please upgrade your plan."
//  }


}
