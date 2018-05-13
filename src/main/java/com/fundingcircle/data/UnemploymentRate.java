package com.fundingcircle.data;

import java.math.BigDecimal;

public class UnemploymentRate {
    private Integer year;
    private BigDecimal averageRate;

    public UnemploymentRate setYear(int year) {
        this.year = year;
        return this;
    }

    public UnemploymentRate setAverageRate(BigDecimal value) {
        averageRate = value;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }
}
