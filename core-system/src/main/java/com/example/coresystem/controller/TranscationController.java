package com.example.coresystem.controller;

import com.example.coresystem.dto.TopUpRequest;
import com.example.coresystem.dto.TopUpResponse;
import com.example.coresystem.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
public class TranscationController {

    //topUp -> setor tunai
    //overbooking
    //inquiry transcations

    @Autowired
    private TransactionServices transactionServices;

    @PostMapping("/top-up")
    public TopUpResponse doTopUp(@RequestBody TopUpRequest request) {
        return transactionServices.topUp(request);
    }

}
