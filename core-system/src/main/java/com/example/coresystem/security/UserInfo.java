package com.example.coresystem.security;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserInfo implements Serializable {
    public static final Long serialVersionUID = 1L;

    private String userId;

    private String password;

    private String userSts;

    private String acctNo;

    private String usrNm;
}
