package com.example.braguia.account;

import com.example.braguia.client.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Schema(description="Account entity")
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Schema(description="Unique identifier of the account", example="1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description="Unique number of the account", example="1234567890")
    private String number;

    @Column(nullable = false, unique = false)
    @Schema(description="Bank agency of the account", example="Agency of central Braga")
    private String agency;

    @Column(nullable = false, unique = false, precision = 19, scale = 2)
    @Schema(description="Money in the account", example="25000")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false)
    @Schema(description="The type of account", example="SAVINGS or CURRENT")
    private AccountType type;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false, unique = false)
    @Schema(description="Unique identifier of the client that owns this account", example="1")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}