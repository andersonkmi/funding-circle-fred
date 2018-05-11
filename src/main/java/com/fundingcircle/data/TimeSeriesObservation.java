package com.fundingcircle.data;

public class TimeSeriesObservation {
    private String date = "";
    private Double value = 0.0;


    public TimeSeriesObservation setDate(String date) {
        this.date = date;
        return this;
    }

    public TimeSeriesObservation setValue(Double value) {
        this.value = value;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }
}
