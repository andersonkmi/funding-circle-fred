package com.fundingcircle.service;

import com.fundingcircle.data.TimeSeriesObservation;
import com.fundingcircle.repository.FederalReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/gdpc")
public class GdpcService {
    @Autowired
    private FederalReserveRepository federalReserveRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/full")
    public @ResponseBody Collection<TimeSeriesObservation> loadAll() {
        return federalReserveRepository.getRealGrossDomesticProduct();
    }
}
