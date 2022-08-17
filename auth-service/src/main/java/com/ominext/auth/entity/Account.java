package com.ominext.auth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * Account
 */
@Entity
@Data
public class Account {

    @Id
    @SequenceGenerator(name = "AccountId", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private Long id;

    @Column(length = 100)
    private String fullName;

    @NotNull
    private String password;

    @Column(length = 200)
    private String mailAddress;

    @Column(length = 200)
    private String ccMailAddress;

    private String accountStatus;

    private Boolean logout;

    @NotNull
    @Column(length = 20)
    private String authorities;

    @Column(length = 100)
    private String hash;

    private boolean deleted;
}
