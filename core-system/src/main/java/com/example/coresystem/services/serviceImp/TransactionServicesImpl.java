package com.example.coresystem.services.serviceImp;

import com.example.coresystem.constan.Constant;
import com.example.coresystem.dto.TopUpRequest;
import com.example.coresystem.dto.TopUpResponse;
import com.example.coresystem.model.*;
import com.example.coresystem.repository.AccountRepository;
import com.example.coresystem.repository.TransactionRepository;
import com.example.coresystem.repository.UserActivityRepository;
import com.example.coresystem.repository.UserRepository;
import com.example.coresystem.services.TransactionServices;
import com.example.coresystem.utils.CommonUtils;
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
    @Transactional
    public Accounts calculation(String senderAcct, String rcvAcct, BigInteger trxAmt) {
        Accounts sender = accountRepository.findByAcctNo(senderAcct).orElseThrow();

        //user do topUp
        if(senderAcct.equals(rcvAcct)) {
            sender.setBalance(sender.getBalance().add(trxAmt));
            sender.setSysUpdDtm(LocalDateTime.now());
            Accounts updSender = accountRepository.save(sender);
            createLog(updSender, trxAmt, BigInteger.ZERO,  Constant.TRX_OVERBOOKING,"CREDIT", null, senderAcct, rcvAcct, "", sender.getUser().getUsrNm(), sender.getUser().getUsrNm() );
            return updSender;
        }

        Accounts receiver= accountRepository.findByAcctNo(rcvAcct).orElseThrow();

        //check currency
        if (!sender.getCurrency().equals(receiver.getCurrency())) {
            throw new RuntimeException("Currency Withdrawal And Beneficiary is not same");
        }

        if(sender.getBalance().compareTo(trxAmt) < 0) {
            throw new RuntimeException("Balance not enough");
        }

        sender.setBalance(sender.getBalance().subtract(trxAmt));
        sender.setSysUpdDtm(LocalDateTime.now());

        Accounts updSender =  accountRepository.save(sender);

        receiver.setBalance(receiver.getBalance().add(trxAmt));
        receiver.setSysUpdDtm(LocalDateTime.now());
        Accounts updRcv = accountRepository.save(receiver);

        createLog(updSender, trxAmt, BigInteger.ZERO, Constant.TRX_OVERBOOKING,"DEBIT", null, senderAcct, rcvAcct, "", sender.getUser().getUsrNm(), receiver.getUser().getUsrNm());
        createLog(updRcv, trxAmt, BigInteger.ZERO,  Constant.TRX_OVERBOOKING,"CREDIT", null, senderAcct, rcvAcct, "",  sender.getUser().getUsrNm(), receiver.getUser().getUsrNm());

        // next crete log
        return updSender;
    }

    private void createLog(
            Accounts account, BigInteger trxAmt, BigInteger trxFee, String trxType,
            String creditDebit, TransactionHistories hist, String senderAcct,
            String rcvAcct, String bankCd, String wdrlNm, String rcvActNm) {

        Transactions logTrx = new Transactions();

        logTrx.setAccount(account); // Set the account
        logTrx.setCurrency(account.getCurrency());
        logTrx.setReffNo(CommonUtils.yyyyMMddHHmmss()); // Generate reference number
        logTrx.setStatus("SUCCESS"); // Set status
        logTrx.setTrxAmt(trxAmt);
        logTrx.setTrxFee(trxFee);
        logTrx.setTrxType(trxType);
        logTrx.setCreditOrDebit(creditDebit);
        logTrx.setTransactionHistories(hist);
        logTrx.setRcvAcctNm(rcvActNm); // Receiver account name, if applicable
        logTrx.setRcvAcctNo(rcvAcct); // Receiver account number
        logTrx.setRcvBankCd(bankCd); // Receiver bank code, if applicable
        logTrx.setRemarkSender("");
        logTrx.setRemarkReceiver("");
        logTrx.setAvailableBalance(account.getBalance());
        logTrx.setWdrlAcctNm(wdrlNm); // Withdrawal account name
        logTrx.setWdrlAcctNo(senderAcct); // Withdrawal account number
        logTrx.setWdrlBankCd(""); // Withdrawal bank code, if applicable
        logTrx.setTrscDt(CommonUtils.currDate());
        logTrx.setTrscTm(CommonUtils.currTime());
        logTrx.setUsrUpdId("0000000"); // User update ID
        logTrx.setSysUpdDtm(LocalDateTime.now());
        logTrx.setUsrRegId("0000000"); // User register ID
        logTrx.setSysRegDtm(LocalDateTime.now());

        transactionRepository.save(logTrx);
    }

}
