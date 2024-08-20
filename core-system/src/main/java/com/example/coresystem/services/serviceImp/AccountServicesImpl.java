package com.example.coresystem.services.serviceImp;

import com.example.coresystem.constan.Constant;
import com.example.coresystem.dto.RequestCard;
import com.example.coresystem.dto.RequestCardResponse;
import com.example.coresystem.model.Accounts;
import com.example.coresystem.model.Users;
import com.example.coresystem.repository.AccountRepository;
import com.example.coresystem.repository.UserActivityRepository;
import com.example.coresystem.repository.UserRepository;
import com.example.coresystem.services.AccountServices;
import com.example.coresystem.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServicesImpl implements AccountServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public RequestCardResponse requestDebitCard(RequestCard req) {
        Users user = userRepository.findByUserId(req.getUserId()).orElseThrow();
        Accounts newAccount = new Accounts();
        newAccount.setAcctNo(CommonUtils.generateAccountNumber());
        newAccount.setCardNo(CommonUtils.generateCardNumber());
        newAccount.setExpiredDt(String.valueOf(LocalDateTime.now().plusYears(2)));
        newAccount.setAcctType(Constant.SAVING_ACCT_CD);
        newAccount.setCurrency(req.getCurrency());
        newAccount.setAcctSts(Constant.ACCT_INACTIVE_CD);
        newAccount.setBalance(BigInteger.valueOf(0));
        newAccount.setCardSts(Constant.ACTIVE_CARD_NO_CD);
        newAccount.setCreateDt(CommonUtils.currDate());
        newAccount.setSysRegDtm(LocalDateTime.now());
        newAccount.setSysUpdDtm(LocalDateTime.now());
        newAccount.setUsrRegId("0000000");
        newAccount.setUsrUpdId("0000000");
        newAccount.setUser(user);
        accountRepository.save(newAccount);

        RequestCardResponse response = new RequestCardResponse();
        response.setUserId(user.getUserId());
        response.setCardSts(Constant.ACTIVE_CARD_NO_CD);
        response.setShippingDate(String.valueOf(LocalDateTime.now().plusWeeks(1)));
        response.setExpiredDt(String.valueOf(LocalDateTime.now().plusYears(2)));

        return response;
    }
}
