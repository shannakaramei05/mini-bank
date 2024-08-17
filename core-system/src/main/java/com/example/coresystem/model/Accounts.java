package com.example.coresystem.model;

import com.example.coresystem.model.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
public class Accounts extends BaseEntity {


    @UniqueElements
    @Column(name = "ACCOUNT_NO")
    @Length(max = 25)
    private String acctNo;

    @UniqueElements
    @Column(name = "CARD_NO")
    @Length(max = 25)
    private String cardNo;

    @Column(name = "ACCOUNT_STS")
    @Length(max = 2)
    private String acctSts;

    @Column(name = "CARD_STS")
    @Length(max = 2)
    private String cardSts;

    @Column(name = "ACCOUNT_TYPE")
    @Length(max = 5)
    private String acctType;

    @Column(name = "BALANCE")
    private BigInteger balance;

    @Column(name = "CURRENCY")
    @Length(max =5)
    private String currency;

    @Column(name = "EXPIRED_DATE")
    @Length(max = 8)
    private String expiredDt;

    @Column(name = "CREATED_DATE")
    @Length(max = 8)
    private String createDt;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Users user;

    @OneToMany(mappedBy = "account")
    List<Transactions> transactions;
}
