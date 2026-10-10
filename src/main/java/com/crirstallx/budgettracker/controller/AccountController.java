package com.crirstallx.budgettracker.controller;

import com.crirstallx.budgettracker.model.Account;
import com.crirstallx.budgettracker.service.FinanceManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final FinanceManager financeManager;

    public AccountController(FinanceManager financeManager) {
        this.financeManager = financeManager;
    }

    @GetMapping
    public List<Account> getAccountList() {
        return financeManager.getAccountList();
    }
}
