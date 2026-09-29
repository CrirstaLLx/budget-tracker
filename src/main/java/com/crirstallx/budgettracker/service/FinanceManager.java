package com.crirstallx.budgettracker.service;

import com.crirstallx.budgettracker.model.Account;
import com.crirstallx.budgettracker.model.Budget;
import com.crirstallx.budgettracker.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private List<Account> accountList;
    private List<Transaction> transactionList;
    private List<Budget> budgetList;

    public FinanceManager() {
        this.accountList = new ArrayList<>();
        this.transactionList = new ArrayList<>();
        this.budgetList = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        if (!accountList.contains(transaction.getAccount())) {
            throw new IllegalArgumentException("Account does not exist");
        }
        transactionList.add(transaction);
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public void addBudget(Budget budget) {
        budgetList.add(budget);
    }
}
