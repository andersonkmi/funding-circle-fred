package com.fundingcircle.service;

import com.fundingcircle.data.OperationResult;
import com.fundingcircle.data.TimeSeriesObservation;
import com.fundingcircle.repository.CustomerSentimentIndexRepository;
import com.fundingcircle.repository.FederalReserveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/umcsent")
public class CustomerSentimentIndexService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSentimentIndexService.class);

    @Autowired
    private FederalReserveRepository federalReserveRepository;

    @Autowired
    private CustomerSentimentIndexRepository customerSentimentIndexRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/performInitialLoad")
    public @ResponseBody OperationResult initialLoad() {
        logger.info("Invoking initial load");
        Collection<TimeSeriesObservation> items = federalReserveRepository.getSentimentIndex();
        int totalInserted = customerSentimentIndexRepository.insertAll(items);
        OperationResult result = new OperationResult();
        result.setCode(0).setMessage("Inserted " + totalInserted + " observations").setTimeSeries("University of Michigan Customer Sentiment Index (UMCSENT)");
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/performIncrementalLoad")
    public @ResponseBody
    OperationResult incrementalLoad() {
        Collection<TimeSeriesObservation> items = federalReserveRepository.getSentimentIndex();
        int totalInserted = customerSentimentIndexRepository.insertIncremental(items);
        OperationResult result = new OperationResult();
        result.setCode(0).setMessage("Inserted " + totalInserted + " observations").setTimeSeries("University of Michigan Customer Sentiment Index (UMCSENT)");
        return result;
    }

}
