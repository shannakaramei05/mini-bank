package com.example.coresystem.services;

import com.example.coresystem.dto.RequestCard;
import com.example.coresystem.dto.RequestCardResponse;
import com.example.coresystem.model.Accounts;

public interface AccountServices {

    //account based on type (3 type for now)
    RequestCardResponse requestDebitCard(RequestCard requestCard);
}
