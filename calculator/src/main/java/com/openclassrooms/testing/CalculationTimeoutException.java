package com.openclassrooms.testing;

public class CalculationTimeoutException extends Exception{

    public CalculationTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
