package com.fundingcircle.data;

import java.util.Date;

public class TimeSeriesObservation {
    private Date date;
    private Double value = 0.0;


    public TimeSeriesObservation setDate(Date date) {
        this.date = date;
        return this;
    }

    public TimeSeriesObservation setValue(Double value) {
        this.value = value;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }
}
