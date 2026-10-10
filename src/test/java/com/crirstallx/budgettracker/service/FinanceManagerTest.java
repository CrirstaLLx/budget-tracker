package com.crirstallx.budgettracker.service;

import com.crirstallx.budgettracker.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FinanceManagerTest {
    private FinanceManager financeManager;
    private Account account;

    @BeforeEach
    public void setUp() {
        financeManager = new FinanceManager();
        account = new Account("RuslanG", new BigDecimal("1000.00"));
        financeManager.addAccount(account);
    }

    @Test
    public void getBalance_includesIncomeAndExpenses() {
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

    @Test
    public void getBudgetStatus_returnsPercentOfLimitSpent() {
        Budget budget = new Budget(ExpenseCategory.FITNESS, new BigDecimal("200.00"), YearMonth.of(2026,10));
        financeManager.addBudget(budget);

        Transaction transaction = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("42.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 10, 1, 0, 0, 0));
        Transaction transaction4 = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("35.00"), "Golem OFF-PEAK FRIEND", LocalDateTime.of(2026, 10, 15, 0, 0, 0));

        financeManager.addTransaction(transaction);
        financeManager.addTransaction(transaction4);

        BigDecimal actual = financeManager.getBudgetStatus(ExpenseCategory.FITNESS, YearMonth.of(2026, 10));
        BigDecimal expected = new BigDecimal("38.50");
        assertEquals(expected, actual);

    }

    @Test
    public void getBudgetStatus_throwsWhenNoBudgetExists() {
        assertThrows(IllegalArgumentException.class,
                () -> financeManager.getBudgetStatus(ExpenseCategory.FITNESS, YearMonth.of(2026, 10)));
    }

    @Test
    public void addBudget_replacesExistingBudgetForSameCategoryAndMonth() {
        Budget budget = new Budget(ExpenseCategory.FITNESS, new BigDecimal("200.00"), YearMonth.of(2026,10));
        financeManager.addBudget(budget);

        Budget budgetNew = new Budget(ExpenseCategory.FITNESS, new BigDecimal("100.00"), YearMonth.of(2026,10));
        financeManager.addBudget(budgetNew);

        Transaction transaction = new Transaction(account, TransactionType.EXPENSE, ExpenseCategory.FITNESS, new BigDecimal("50.00"), "Golem OFF-PEAK", LocalDateTime.of(2026, 10, 1, 0, 0, 0));
        financeManager.addTransaction(transaction);

        BigDecimal actual = financeManager.getBudgetStatus(ExpenseCategory.FITNESS, YearMonth.of(2026, 10));
        BigDecimal expected = new BigDecimal("50.00");
        assertEquals(expected, actual);
    }

}
