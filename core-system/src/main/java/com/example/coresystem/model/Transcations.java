package com.example.coresystem.model;

import com.example.coresystem.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
public class Transcations extends BaseEntity {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "REFF_NO")
    @Length(max = 12)
    private String reffNo;

    @Column(name = "TRSC_DT")
    @Length(max = 8)
    private String trscDt;

    @Column(name = "TRSC_TM")
    @Length(max = 4)
    private String trscTm;

    @Column(name = "WDRL_ACCT_NO")
    @Length(max = 25)
    private String wdrlAcctNo;

    @Column(name = "AVAILABLE_BALANCE")
    private BigInteger availableBalance;

    @Column(name = "WDRL_ACCT_NM")
    @Length(max = 50)
    private String wdrlAcctNm;

    @Column(name = "WDRL_BANK_CD")
    @Length(max = 5)
    private String wdrlBankCd;

    @Column(name = "REMARK_SENDER")
    @Length(max = 50)
    private String remarkSender;

    @Column(name = "RCV_ACCT_NO")
    @Length(max = 50)
    private String rcvAcctNo;

    @Column(name = "RCV_ACCT_NM")
    @Length(max = 50)
    private String rcvAcctNm;

    @Column(name = "RCV_BANK_CD")
    @Length(max = 5)
    private String rcvBankCd;

    @Column(name = "REMARK_RCV")
    @Length(max = 50)
    private String remarkReceiver;

    @Column(name = "TRX_AMT")
    private BigInteger trxAmt;

    @Column(name = "TRX_FEE")
    private BigInteger trxFee;

    @Column(name = "CURRENCY")
    @Length(max = 5)
    private String currency;

    @Column(name = "TRX_TYPE")
    @Length(max = 2)
    private String trxType;

    @Column(name = "STATUS")
    @Length(max = 10)
    private String status;

}
