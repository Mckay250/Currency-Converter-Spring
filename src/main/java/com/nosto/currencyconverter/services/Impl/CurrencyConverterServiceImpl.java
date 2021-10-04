package com.nosto.currencyconverter.services.Impl;

import com.nosto.currencyconverter.dtos.ServiceResponse;
import com.nosto.currencyconverter.dtos.requests.CurrencyConversionRequest;
import com.nosto.currencyconverter.dtos.responses.CurrencyConversionResponse;
import com.nosto.currencyconverter.dtos.responses.CurrencyRateResponse;
import com.nosto.currencyconverter.exceptions.ConverterServiceException;
import com.nosto.currencyconverter.services.CurrencyConverterService;
import com.nosto.currencyconverter.services.CurrencyRateService;
import com.nosto.currencyconverter.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConverterServiceImpl.class);

    private final CurrencyRateService currencyRateService;

    @Autowired
    public CurrencyConverterServiceImpl(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @Override
    public ServiceResponse<CurrencyConversionResponse> convertCurrency(CurrencyConversionRequest request) {
        ServiceResponse<CurrencyConversionResponse> response = new ServiceResponse<>(Messages.ERROR, Messages.GENERAL_ERROR_MESSAGE);
        CurrencyConversionResponse data = new CurrencyConversionResponse();
        data.setBaseCurrencySymbol(request.getBaseCurrencySymbol());
        data.setOutputCurrencySymbol(request.getOutputCurrencySymbol());
        data.setInputAmount(request.getAmount());
        data.setOutputAmount(request.getAmount());

        if (request.getAmount() <= 0) {
            return response.setMessage(Messages.ZERO_OR_NEGATIVE_AMOUNT)
                    .setData(data);
        }

        CurrencyRateResponse currencyRateResponse;
        try {
            currencyRateResponse = currencyRateService.getCurrencyRate(
                    request.getBaseCurrencySymbol(), request.getOutputCurrencySymbol()
            );
            data.setOutputAmount(currencyRateResponse.getRate() * data.getInputAmount());
            return response.setCode(Messages.SUCCESS).setMessage(Messages.GENERAL_SUCCESS_MESSAGE).setData(data);
        } catch (ConverterServiceException ex) {
            LOGGER.error("ERROR: {} {}", ex.getStatusCode(), ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }



}
