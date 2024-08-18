package com.example.coresystem.model;

import com.example.coresystem.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class Users extends BaseEntity {

    @Column(name = "USER_ID")
    @Length(max = 25)
    private String userId;

    @Column(name = "LOGIN_PASSWORD")
    @Length(max = 100)
    private String password;


    @Column(name = "DEBIT_PIN_NO")
    @Length(max=6)
    private String debitPinNo;

    @Column(name = "USER_NM")
    @Length(max = 50)
    private String usrNm;

    @Column(name = "EMAIL")
    @Length(max=100)
    @Email
    private String email;

    @Column(name = "PHONE_NO")
    @Length(max = 20)
    private String phoneNo;

    @Column(name = "ADDRESS")
    @Length(max=45)
    private String address;

    @Column(name = "STATAUS")
    @Length(max =2)
    private String status;

    @Column(name = "JOB")
    @Length(max = 45)
    private String job;

    @Column(name = "COUNTRY")
    @Length(max = 5)
    private String country;

    @Column(name = "IS_ADMIN")
    private boolean isAdmin;

    @Column(name = "USR_REGIST_DT")
    @Length(max = 8)
    private  String usrRegDt;


    @OneToOne(mappedBy = "user")
    private UserActivities userActivities;


    @OneToMany(mappedBy ="user" )
    private List<Accounts> accounts;

}
