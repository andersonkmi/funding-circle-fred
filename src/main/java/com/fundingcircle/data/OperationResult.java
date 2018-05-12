package com.fundingcircle.data;

public class OperationResult {
    private int code;
    private String message;

    public OperationResult setCode(int code) {
        this.code = code;
        return this;
    }

    public OperationResult setMessage(String message) {
        this.message = message;
    }
}
