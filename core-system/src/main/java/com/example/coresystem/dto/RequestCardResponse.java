package com.example.coresystem.dto;

import lombok.Data;

@Data
public class RequestCardResponse {

    private String userId;
    private String cardNo;
    private String cardSts;
    private String expiredDt;
    private String shippingDate;

}
