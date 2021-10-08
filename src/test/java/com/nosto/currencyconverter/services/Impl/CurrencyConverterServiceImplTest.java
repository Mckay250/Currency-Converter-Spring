package com.nosto.currencyconverter.services.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nosto.currencyconverter.dtos.ServiceResponse;
import com.nosto.currencyconverter.dtos.requests.CurrencyConversionRequest;
import com.nosto.currencyconverter.dtos.responses.CurrencyConversionResponse;
import com.nosto.currencyconverter.dtos.responses.CurrencyRateResponse;
import com.nosto.currencyconverter.exceptions.ConverterServiceException;
import com.nosto.currencyconverter.services.CurrencyRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CurrencyConverterServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CurrencyConverterServiceImplTest {
    @Autowired
    private CurrencyConverterServiceImpl currencyConverterServiceImpl;

    @MockBean
    private CurrencyRateService currencyRateService;

    @Test
    void testConvertCurrency_whenCurrencyPairsAreTheSame() {
        ServiceResponse<CurrencyConversionResponse> actualConvertCurrencyResult = this.currencyConverterServiceImpl
                .convertCurrency(new CurrencyConversionRequest("GBP", "GBP", 10.0));
        assertEquals(0, actualConvertCurrencyResult.getCode());
        assertEquals(
                "ServiceResponse{code=0, message='Successful', object=CurrencyConversionResponse(baseCurrencySymbol=null,"
                        + " outputCurrencySymbol=null, inputAmount=10.0, outputAmount=10.0)}",
                actualConvertCurrencyResult.toString());
        assertEquals("Successful", actualConvertCurrencyResult.getMessage());
        CurrencyConversionResponse data = actualConvertCurrencyResult.getData();
        assertEquals(10.0, data.getOutputAmount());
        assertEquals(10.0, data.getInputAmount());
    }

    @Test
    void testConvertCurrency_whenCurrencyPairsAreDifferent() {
        CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
        currencyRateResponse.setBaseCurrencySymbol("USD");
        currencyRateResponse.setRate(10.0);
        currencyRateResponse.setOutputCurrencySymbol("GBP");
        when(this.currencyRateService.getCurrencyRate((String) any(), (String) any())).thenReturn(currencyRateResponse);
        ServiceResponse<CurrencyConversionResponse> actualConvertCurrencyResult = this.currencyConverterServiceImpl
                .convertCurrency(new CurrencyConversionRequest("USD", "GBP", 10.0));
        assertEquals(0, actualConvertCurrencyResult.getCode());
        assertEquals(
                "ServiceResponse{code=0, message='Successful', object=CurrencyConversionResponse(baseCurrencySymbol=USD,"
                        + " outputCurrencySymbol=GBP, inputAmount=10.0, outputAmount=100.0)}",
                actualConvertCurrencyResult.toString());
        assertEquals("Successful", actualConvertCurrencyResult.getMessage());
        CurrencyConversionResponse data = actualConvertCurrencyResult.getData();
        assertEquals("USD", data.getBaseCurrencySymbol());
        assertEquals("GBP", data.getOutputCurrencySymbol());
        assertEquals(100.0, data.getOutputAmount());
        assertEquals(10.0, data.getInputAmount());
        verify(this.currencyRateService).getCurrencyRate((String) any(), (String) any());
    }

    @Test
    void testConvertCurrency_whenAnErrorHasOccured() {
        when(this.currencyRateService.getCurrencyRate((String) any(), (String) any()))
                .thenThrow(new ConverterServiceException("An error occurred", 1));
        ServiceResponse<CurrencyConversionResponse> actualConvertCurrencyResult = this.currencyConverterServiceImpl
                .convertCurrency(new CurrencyConversionRequest("USD", "GBP", 10.0));
        assertEquals(96, actualConvertCurrencyResult.getCode());
        assertEquals("An error occurred", actualConvertCurrencyResult.getMessage());
        verify(this.currencyRateService).getCurrencyRate((String) any(), (String) any());
    }

    @Test
    void testConvertCurrency_whenAmountIsLessThan1() {
        CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
        currencyRateResponse.setBaseCurrencySymbol("USD");
        currencyRateResponse.setRate(10.0);
        currencyRateResponse.setOutputCurrencySymbol("GBP");
        when(this.currencyRateService.getCurrencyRate((String) any(), (String) any())).thenReturn(currencyRateResponse);
        ServiceResponse<CurrencyConversionResponse> actualConvertCurrencyResult = this.currencyConverterServiceImpl
                .convertCurrency(new CurrencyConversionRequest("USD", "GBP", 0.0));
        assertEquals(96, actualConvertCurrencyResult.getCode());
        assertEquals("ServiceResponse{code=96, message='Amount cannot be equal to or less than zero', object"
                + "=CurrencyConversionResponse(baseCurrencySymbol=null, outputCurrencySymbol=null, inputAmount=0.0,"
                + " outputAmount=0.0)}", actualConvertCurrencyResult.toString());
        assertEquals("Amount cannot be equal to or less than zero", actualConvertCurrencyResult.getMessage());
        CurrencyConversionResponse data = actualConvertCurrencyResult.getData();
        assertEquals(0.0, data.getOutputAmount());
        assertEquals(0.0, data.getInputAmount());
    }
}

