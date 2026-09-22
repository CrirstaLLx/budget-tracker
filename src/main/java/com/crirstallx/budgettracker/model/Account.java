package com.crirstallx.budgettracker.model;

import java.math.BigDecimal;

public class Account {
    private String accountName;
    private BigDecimal balance;

    public Account(String accountName, BigDecimal balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
