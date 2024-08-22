package com.example.coresystem.dto;

import lombok.Data;

@Data
public class ActivationCardRequest {

    private String userId;
    private String debitPinNo;
    private String cardNo;
    private String expireDate;

}
