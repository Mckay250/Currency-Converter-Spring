package com.nosto.currencyconverter.controller;


import com.nosto.currencyconverter.dtos.ServiceResponse;
import com.nosto.currencyconverter.models.Currency;
import com.nosto.currencyconverter.services.CurrencyService;
import com.nosto.currencyconverter.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
public class CurrencyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService currencyService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse<List<Currency>>> fetchCurrencies() {
        try {
            ServiceResponse<List<Currency>> serviceResponse = new ServiceResponse<>(Messages.SUCCESS, Messages.GENERAL_SUCCESS_MESSAGE);
            serviceResponse.setData(currencyService.getAllCurrencies());
            return ResponseEntity.ok(serviceResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
