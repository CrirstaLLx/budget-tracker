package com.crirstallx.budgettracker.model;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Budget {
    private ExpenseCategory expenseCategory;
    private BigDecimal amount;
    private YearMonth yearMonth;

    public Budget(ExpenseCategory expenseCategory, BigDecimal amount, YearMonth yearMonth) {
        this.expenseCategory = expenseCategory;
        this.amount = amount;
        this.yearMonth = yearMonth;
    }

    public ExpenseCategory getExpenseCategory() { return expenseCategory; }
    public BigDecimal getAmount() { return amount; }
    public YearMonth getYearMonth() { return yearMonth; }

}
