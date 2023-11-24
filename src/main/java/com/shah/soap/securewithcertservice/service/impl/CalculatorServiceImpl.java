package com.shah.soap.securewithcertservice.service.impl;

import com.shah.soap.securewithcertservice.service.CalculatorService;
import com.shah.soap.securewithcertservice.dto.CalculateSumRequest;
import com.shah.soap.securewithcertservice.dto.CalculateSumResponse;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Service;

@Service
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public CalculateSumResponse sum(CalculateSumRequest calculateSumRequest) {
        return new CalculateSumResponse(calculateSumRequest.getNumber1() +calculateSumRequest.getNumber2());
    }
}
