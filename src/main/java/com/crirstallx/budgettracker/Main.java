package com.crirstallx.budgettracker;

import com.crirstallx.budgettracker.model.Account;
import com.crirstallx.budgettracker.model.ExpenseCategory;
import com.crirstallx.budgettracker.model.Transaction;
import com.crirstallx.budgettracker.model.TransactionType;
import com.crirstallx.budgettracker.service.FinanceManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();
        Account account = new Account("RuslanG", new BigDecimal("1000.00"));
        financeManager.addAccount(account);

        Transaction transaction = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("42.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 10, 1, 0, 0, 0));
        Transaction transaction2 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.SUPERMARKETS, new BigDecimal("25.00"), "BILLA 012", LocalDateTime.of(2026, 10, 5, 0, 0, 0));
        Transaction transaction3 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.EDUCATION, new BigDecimal("20.00"), "STU ISIC", LocalDateTime.of(2026, 10, 3, 0, 0, 0));
        Transaction transaction4 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("35.00"), "Golem OFF-PEAK FRIEND", LocalDateTime.of(2026, 10, 15, 0, 0, 0));
        Transaction transaction5 = new Transaction(account, TransactionType.INCOME, null, new BigDecimal("50.00"), "Fio banka 50 EUR", LocalDateTime.of(2026, 10, 15, 0, 0, 0));
        Transaction transaction6 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("42.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 9, 20, 0, 0, 0));

        financeManager.addTransaction(transaction);
        financeManager.addTransaction(transaction2);
        financeManager.addTransaction(transaction3);
        financeManager.addTransaction(transaction4);
        financeManager.addTransaction(transaction5);
        financeManager.addTransaction(transaction6);

        System.out.println(financeManager.getBalance(account));
        System.out.println(financeManager.getExpensesByCategory(YearMonth.of(2026, 10)));

    }
}
