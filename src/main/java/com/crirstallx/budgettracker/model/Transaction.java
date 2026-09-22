package com.crirstallx.budgettracker.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Account account;
    private TransactionType type;
    private ExpenseCategory expenseCategory;
    private BigDecimal amount;
    private String description;
    private LocalDateTime dateTime;

    public Transaction(Account account, TransactionType type, ExpenseCategory expenseCategory, BigDecimal amount, String description, LocalDateTime dateTime) {
        this.account = account;
        this.type = type;
        this.expenseCategory = expenseCategory;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TransactionType getType() {
        return type;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
