package com.example.coresystem.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class TopUpResponse {

    private String acctNo;
    private String usrNm;
    private BigInteger afterBalance;
    private LocalDateTime trxTime;
}
