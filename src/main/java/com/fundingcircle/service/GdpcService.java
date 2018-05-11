package com.fundingcircle.service;

import com.fundingcircle.data.TimeSeriesObservation;
import com.fundingcircle.repository.FederalReserveRepository;
import com.fundingcircle.repository.GrossDevelopedProductRepository;
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

    @Autowired
    private GrossDevelopedProductRepository grossDevelopedProductRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/initial")
    public @ResponseBody Collection<TimeSeriesObservation> initialLoad() {
        grossDevelopedProductRepository.truncate();
        return federalReserveRepository.getRealGrossDomesticProduct();
    }
}
