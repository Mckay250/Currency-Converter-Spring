package com.nosto.currencyconverter.exceptions;

public class ConverterServiceException extends RuntimeException {

	private static final long serialVersionUID = -101L;

	private final int statusCode;

	public ConverterServiceException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}


//	public ClientErrorException(Throwable error) {
//		super(error.getLocalizedMessage(), error);
//	}
}
