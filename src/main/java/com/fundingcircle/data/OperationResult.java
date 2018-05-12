package com.fundingcircle.data;

public class OperationResult {
    private String timeSeries;
    private int code;
    private String message;

    public OperationResult setTimeSeries(String name) {
        timeSeries = name;
        return this;
    }

    public OperationResult setCode(int code) {
        this.code = code;
        return this;
    }

    public OperationResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSeries() {
        return timeSeries;
    }
}
