package com.crirstallx.budgettracker.service;

import com.crirstallx.budgettracker.model.Account;
import com.crirstallx.budgettracker.model.ExpenseCategory;
import com.crirstallx.budgettracker.model.Transaction;
import com.crirstallx.budgettracker.model.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinanceManagerTest {
    @Test
    public void getBalance_includesIncomeAndExpenses() {
        FinanceManager financeManager = new FinanceManager();
        Account account = new Account("RuslanG", new BigDecimal("1000.00"));
        financeManager.addAccount(account);

        Transaction transaction5 = new Transaction(account, TransactionType.INCOME, null, new BigDecimal("50.00"), "Fio banka 50 EUR", LocalDateTime.of(2026, 10, 15, 0, 0, 0));
        Transaction transaction6 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("42.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 9, 20, 0, 0, 0));

        financeManager.addTransaction(transaction5);
        financeManager.addTransaction(transaction6);

        BigDecimal expected = new BigDecimal("1008.00");
        BigDecimal actual = financeManager.getBalance(account);

        assertEquals(expected, actual);
    }

    @Test
    public void getExpensesByCategory_sumsOnlyGivenMonth() {
        FinanceManager financeManager = new FinanceManager();
        Account account = new Account("RuslanG", new BigDecimal("1000.00"));
        financeManager.addAccount(account);

        Transaction transaction = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("42.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 10, 1, 0, 0, 0));
        Transaction transaction4 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("35.00"), "Golem OFF-PEAK FRIEND", LocalDateTime.of(2026, 10, 15, 0, 0, 0));
        Transaction transaction6 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("42.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 9, 20, 0, 0, 0));

        financeManager.addTransaction(transaction);
        financeManager.addTransaction(transaction4);
        financeManager.addTransaction(transaction6);

        Map<ExpenseCategory, BigDecimal> result = financeManager.getExpensesByCategory(YearMonth.of(2026, 10));
        BigDecimal expected = new BigDecimal("77.00");
        BigDecimal actual = result.get(ExpenseCategory.FITNESS);
        assertEquals(expected, actual);
    }
}
