package com.example.braguia.account;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
@Tag(name="Accounts", description="Bank Accounts management APIs")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Create a bank account for an existing client")
    @PostMapping("/add")
    public String addAccount(@Valid @RequestParam String number, @Valid @RequestParam String agency, @Valid @RequestParam BigDecimal balance, @Valid @RequestParam AccountType type, @Valid @RequestParam Long client_id) {
        boolean result = accountService.addAccount(number, agency, balance, type, client_id);
        if (result) { return "Saved"; }
        else { return "Could not add account"; }
    }

    @Operation(summary = "Get a list of all of the bank's accounts")
    @GetMapping("/all")
    public Iterable<Account> getAllAccounts() {
        Iterable<Account> accounts = accountService.getAllAccounts();

        return accounts;
    }

    @Operation(summary = "Get the amount of money available in a bank account at the current time")
    @GetMapping("/statement")
    public BigDecimal getAccountStatement(@Valid @RequestParam Long id) {
        BigDecimal statement = accountService.getAccountStatement(id);

        return statement;
    }
}