package com.example.coresystem.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "SYS_REG_DTM")
    private LocalDateTime sysRegDtm;

    @UpdateTimestamp
    @Column(name = "SYS_UPD_DTM")
    private LocalDateTime sysUpdDtm;

    @Column(name = "USR_REG_ID")
    @Length(max = 25)
    private String usrRegId;

    @Column(name = "USR_UPD_ID")
    @Length(max = 25)
    private String usrUpdId;
}
