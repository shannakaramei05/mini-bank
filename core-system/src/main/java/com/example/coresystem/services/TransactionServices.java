package com.example.coresystem.services;

import com.example.coresystem.dto.TopUpRequest;
import com.example.coresystem.dto.TopUpResponse;

public interface TransactionServices {
    TopUpResponse topUp(TopUpRequest request);
}
