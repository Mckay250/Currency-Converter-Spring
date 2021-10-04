package com.nosto.currencyconverter.controller;

import com.nosto.currencyconverter.dtos.ServiceResponse;
import com.nosto.currencyconverter.dtos.requests.CurrencyConversionRequest;
import com.nosto.currencyconverter.dtos.responses.CurrencyConversionResponse;
import com.nosto.currencyconverter.services.CurrencyConverterService;
import com.nosto.currencyconverter.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;


@RequestMapping("api/v1/currency-converter")
@RestController
public class CurrencyConverterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConverterController.class);

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @RequestMapping(path = "convert", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<CurrencyConversionResponse>> convertCurrency(@Validated @RequestBody CurrencyConversionRequest request) {
        try {
            ServiceResponse<CurrencyConversionResponse> serviceResponse = currencyConverterService.convertCurrency(request);
            return ResponseEntity.ok(serviceResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
