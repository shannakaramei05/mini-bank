package com.example.coresystem.model;

import com.example.coresystem.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "USER_ACTIVITY")
@Getter
@Setter
public class UserActivities extends BaseEntity {


    @Column(name = "LOGIN_PASSWORD")
    @Length(max = 100)
    private String password;


    @Column(name = "DEBIT_PIN_NO")
    @Length(max=6)
    private String debitPinNo;


    @Column(name = "PASSWD_ERR_TCNT")
    @Length(max = 2)
    private int psswdErrCnt;


    @Column(name = "DEBIT_ERR_TCNT")
    @Length(max = 2)
    private int debitErrCnt;


    @Column(name = "LGN_ERR_TCNT")
    @Length(max = 2)
    private int lgnErrCnt;

    @Column(name = "PREV_PASSWORD")
    @Length(max = 100)
    private String oldPassword;

    @Column(name = "LAST_LOGIN_DATE")
    @Length(max = 8)
    private String lstLgnDt;

    @Column(name = "LAST_LOGIN_TM")
    @Length(max = 4)
    private String lstLgnTm;

    @Column(name = "LAST_ACCT_USE")
    @Length(max=25)
    private String lstAcctUse;

    @Column(name = "IS_CHG_PASSWD")
    @Length(max = 1)
    private boolean isChgPw;

    @Column(name = "CHANGE_PASSWD_DT")
    @Length(max = 8)
    private String changePwDt;

    @Column(name = "LOGIN_STATUS")
    @Length(max=2)
    private String loginSts;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private Users user;
}
