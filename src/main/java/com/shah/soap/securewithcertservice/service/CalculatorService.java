package com.shah.soap.securewithcertservice.service;

import com.shah.soap.securewithcertservice.dto.CalculateSumRequest;
import com.shah.soap.securewithcertservice.dto.CalculateSumResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService
public interface CalculatorService {
    @WebMethod
    @WebResult(name = "calculateSumResponse") CalculateSumResponse sum(@WebParam(name = "calculateSumRequest") CalculateSumRequest calculateSumRequest);
}
