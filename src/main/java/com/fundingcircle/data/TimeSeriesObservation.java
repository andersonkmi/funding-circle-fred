package com.fundingcircle.data;

import java.util.Calendar;

public class TimeSeriesObservation {
    private Calendar date;
    private Double value = 0.0;


    public TimeSeriesObservation setDate(Calendar date) {
        this.date = date;
        return this;
    }

    public TimeSeriesObservation setValue(Double value) {
        this.value = value;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }
}
