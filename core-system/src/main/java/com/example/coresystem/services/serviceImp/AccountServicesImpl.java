package com.example.coresystem.services.serviceImp;

import com.example.coresystem.constan.Constant;
import com.example.coresystem.dto.ActivationCardRequest;
import com.example.coresystem.dto.ActivationCardResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RequestCardResponse requestDebitCard(RequestCard req) {
        Users user = userRepository.findByUserId(req.getUserId()).orElseThrow();
        user.setAddress(req.getAddress());
        user.setCountry(req.getCountry());
        user.setPhoneNo(req.getPhoneNo());
        userRepository.save(user);

        Accounts newAccount = new Accounts();
        newAccount.setAcctNo(CommonUtils.generateAccountNumber());
        newAccount.setCardNo(CommonUtils.generateCardNumber());
        newAccount.setExpiredDt(CommonUtils.curDateAddYear(2));
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
        newAccount = accountRepository.save(newAccount);

        RequestCardResponse response = new RequestCardResponse();
        response.setUserId(user.getUserId());
        response.setCardNo(newAccount.getCardNo());
        response.setCardSts(Constant.ACTIVE_CARD_NO_CD);
        response.setShippingDate(String.valueOf(LocalDateTime.now().plusWeeks(1)));
        response.setExpiredDt(String.valueOf(LocalDateTime.now().plusYears(2)));

        return response;
    }

    @Override
    @Transactional
    public ActivationCardResponse activateCard(ActivationCardRequest request) {
        Users user = userRepository.findByUserId(request.getUserId()).orElseThrow();
        Accounts accounts = accountRepository.findByCardNo(request.getCardNo()).orElseThrow();

        ActivationCardResponse activationCardResponse = new ActivationCardResponse();
        //validate accountName with currentUser
        if(request.getUserId().equals(accounts.getUser().getUserId())) {
            user.setDebitPinNo(passwordEncoder.encode(request.getDebitPinNo()));
            user.setStatus(Constant.USR_ACTIVE_CD);
            user.setSysUpdDtm(LocalDateTime.now());
            userRepository.save(user);

            accounts.setAcctSts(Constant.ACCT_ACTIVE_CD);
            accounts.setSysUpdDtm(LocalDateTime.now());
            accountRepository.save(accounts);

            activationCardResponse.setMessage("Card Activation Success, Now you can user your Card and Account for transaction");
            activationCardResponse.setAcctNo(accounts.getAcctNo());
            activationCardResponse.setAcctCur(accounts.getCurrency());
            activationCardResponse.setUserId(user.getUserId());

            return activationCardResponse;
        }

        activationCardResponse.setMessage("You Card is Invalid");
        return activationCardResponse;
    }
}
