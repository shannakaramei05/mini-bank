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
@Table(name = "TRANSACTIONS_HISTORY")
@Getter
@Setter
public class TransactionHistories extends BaseEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "TRSC_DT")
    @Length(max = 8)
    private String trscDt;

    @Column(name = "ACCOUNT_NO")
    @Length(max = 25)
    private String acctNo;

    @Column(name = "TRX_TYPE")
    @Length(max = 2)
    private String trxType;

    @Column(name = "DESCRIPTIONS")
    @Length(max = 50)
    private String desc;

    @Column(name = "TRX_AMOUNT")
    private BigInteger trxAmt;

    @Column(name = "STATUS")
    @Length(max = 1)
    private Boolean sts;

    @Column(name = "BALANCE_AFTER")
    private BigInteger balance;

}
