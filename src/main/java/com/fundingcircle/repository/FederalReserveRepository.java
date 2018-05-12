package com.fundingcircle.repository;

import com.fundingcircle.data.TimeSeriesObservation;
import com.fundingcircle.util.HttpUtil;
import com.fundingcircle.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FederalReserveRepository {
    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private FederalReserveRepositoryConfig federalReserveRepositoryConfig;

    public Collection<TimeSeriesObservation> getRealGrossDomesticProduct() {
        return getData(federalReserveRepositoryConfig.getGdpcUrl());
    }

    public Collection<TimeSeriesObservation> getSentimentIndex() {
        return getData(federalReserveRepositoryConfig.getUmcSentUrl());
    }

    public Collection<TimeSeriesObservation> getUnemploymentRate() {
        return getData(federalReserveRepositoryConfig.getUnrateUrl());
    }

    private Collection<TimeSeriesObservation> getData(String url) {
        try {
            String result = httpUtil.getRequest(url);
            Collection<TimeSeriesObservation> items = jsonUtil.buildTimeSeriesObservationList(result);
            return items;
        } catch (Exception exception) {
            throw new IntegrationException(exception.getMessage(), exception);
        }
    }
}
