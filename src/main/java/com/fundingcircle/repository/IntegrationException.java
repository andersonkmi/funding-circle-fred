package com.fundingcircle.repository;

public class IntegrationException extends RuntimeException {
    public IntegrationException(String message) {
        super(message);
    }

    public IntegrationException(String message, Throwable exception) {
        super(message, exception);
    }
}
