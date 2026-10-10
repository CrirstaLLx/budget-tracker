package com.crirstallx.budgettracker.service;

import com.crirstallx.budgettracker.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.*;

@Service
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
        budgetList.removeIf(b -> b.getExpenseCategory().equals(budget.getExpenseCategory()) && b.getYearMonth().equals(budget.getYearMonth()));
        budgetList.add(budget);
    }

    public BigDecimal getBalance(Account account) {
        BigDecimal balance = account.getBalance();
        for (Transaction transaction : transactionList) {
            if (transaction.getAccount().equals(account)) {
                if (transaction.getType() == TransactionType.INCOME) {
                    balance = balance.add(transaction.getAmount());
                } else if (transaction.getType() == TransactionType.EXPENSE) {
                    balance = balance.subtract(transaction.getAmount());
                }
            }
        }
        return balance;
    }

    public Map<ExpenseCategory, BigDecimal> getExpensesByCategory(YearMonth month) {
        Map<ExpenseCategory, BigDecimal> expenses = new EnumMap<>(ExpenseCategory.class);
        for (Transaction transaction : transactionList) {
            if (transaction.getType() == TransactionType.EXPENSE && YearMonth.from(transaction.getDateTime()).equals(month)) {
                expenses.merge(transaction.getExpenseCategory(), transaction.getAmount(), BigDecimal::add);
            }
        }
        return expenses;
    }

    public BigDecimal getBudgetStatus(ExpenseCategory category, YearMonth month) {
        Budget budget = budgetList.stream()
                .filter(b -> b.getExpenseCategory().equals(category) && b.getYearMonth().equals(month))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No budget found"));
        BigDecimal spent = getExpensesByCategory(month).getOrDefault(category, BigDecimal.ZERO);
        return spent.multiply(new BigDecimal("100")).divide(budget.getAmount(), 2, RoundingMode.HALF_UP);
    }

    public List<Account> getAccountList() {
        return accountList;
    }
}
