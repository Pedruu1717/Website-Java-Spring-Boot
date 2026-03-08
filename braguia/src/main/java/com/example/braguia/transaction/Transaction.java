package com.example.braguia.transaction;

import com.example.braguia.account.Account;
import com.example.braguia.client.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;


import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
@Schema(description="Transaction entity")
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Schema(description="Unique identifier of the transaction", example="1")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false)
    @NotNull
    @Schema(description="The type of transaction", example="WITHDRAWAL, DEPOSIT or TRANSFER")
    private TransactionType type;

    @Column(nullable = false, unique = false, precision = 19, scale = 2)
    @NotNull
    @Schema(description="Amount of money associated with the transaction", example="1000")
    private BigDecimal value;

    @Column(nullable = false, unique = false, columnDefinition="TIMESTAMP")
    @NotNull
    @Schema(description="Date and time of the transaction", example="2026-03-01 17:29:32")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="source_account_id", nullable = false, unique = false)
    @NotNull
    @Schema(description="Unique identifier of the account from where the money is being transferred", example="2")
    private Account source_account;

    @ManyToOne
    @JoinColumn(name="destination_account_id", nullable = false, unique = false)
    @NotNull
    @Schema(description="Unique identifier of the account to which the money is being transferred", example="1")
    private Account destination_account;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false, unique = false)
    @NotNull
    @Schema(description="Unique identifier of the client that owns the source account and decides to make the transfer", example="1")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getSourceAccount() {
        return source_account;
    }

    public void setSourceAccount(Account source_account) {
        this.source_account = source_account;
    }

    public Account getDestinationAccount() {
        return destination_account;
    }

    public void setDestinationAccount(Account destination_account) {
        this.destination_account = destination_account;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}