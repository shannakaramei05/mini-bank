package com.example.coresystem.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class TopUpRequest {

    private String userID;
    private String acctNo;
    private BigInteger topUpBalance;
    private String rcvAcct;
    private String reasons;
}
