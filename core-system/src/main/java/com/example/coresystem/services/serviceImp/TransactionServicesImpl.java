package com.example.coresystem.services.serviceImp;

import com.example.coresystem.dto.TopUpRequest;
import com.example.coresystem.dto.TopUpResponse;
import com.example.coresystem.model.Accounts;
import com.example.coresystem.model.Transactions;
import com.example.coresystem.model.UserActivities;
import com.example.coresystem.model.Users;
import com.example.coresystem.repository.AccountRepository;
import com.example.coresystem.repository.TransactionRepository;
import com.example.coresystem.repository.UserActivityRepository;
import com.example.coresystem.repository.UserRepository;
import com.example.coresystem.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServicesImpl implements TransactionServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    @Transactional
    public TopUpResponse topUp(TopUpRequest request) {
        TopUpResponse response = new TopUpResponse();
        Users users = userRepository.findByUserId(request.getUserID()).orElseThrow();

        Accounts updateAcct = calculation(request.getAcctNo(), request.getRcvAcct(), request.getTopUpBalance());
        response.setAcctNo(updateAcct.getAcctNo());
        response.setAfterBalance(updateAcct.getBalance());
        response.setTrxTime(LocalDateTime.now());
        response.setUsrNm(updateAcct.getUser().getUsrNm());


        //create log trx
//        UserActivities usrActv = userActivityRepository.findByUserUserId(users.getUserId()).orElseThrow().getUserActivities();
//        usrActv.setLstAcctUse(request.getAcctNo());
//        usrActv.setSysUpdDtm(LocalDateTime.now());
//        userActivityRepository.save(usrActv);

        return response;

    }


    //for transactions topup and overbooking
    public Accounts calculation(String senderAcct, String rcvAcct, BigInteger trxAmt) {
        Accounts sender = accountRepository.findByAcctNo(senderAcct).orElseThrow();

        //user do topUp
        if(senderAcct.equals(rcvAcct)) {
            sender.setBalance(sender.getBalance().add(trxAmt));
            sender.setSysUpdDtm(LocalDateTime.now());
            accountRepository.save(sender);
            return sender;
        }

        Accounts receiver= accountRepository.findByAcctNo(rcvAcct).orElseThrow();

        //check currency
        if (sender.getCurrency().equals(receiver.getCurrency())) {
            if(sender.getBalance().compareTo(trxAmt) < 0) {
                throw new RuntimeException("Balance not enough");
            }

            sender.setBalance(sender.getBalance().subtract(trxAmt));
            sender.setSysUpdDtm(LocalDateTime.now());

            accountRepository.save(sender);

            receiver.setBalance(receiver.getBalance().add(trxAmt));
            receiver.setSysUpdDtm(LocalDateTime.now());
            accountRepository.save(receiver);
        }


        // next crete log
        return sender;
    }

}
