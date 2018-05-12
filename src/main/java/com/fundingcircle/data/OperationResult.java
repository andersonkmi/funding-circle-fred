package com.fundingcircle.data;

public class OperationResult {
    private String timeSeriesName;
    private int code;
    private String message;

    public OperationResult setTimeSeriesName(String name) {
        timeSeriesName = name;
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

    public String getTimeSeriesName() {
        return timeSeriesName;
    }
}
