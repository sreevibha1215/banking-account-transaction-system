
package com.banking.system.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(name = "account_type", nullable = false, length = 10)
    private String accountType;

    @Column(name = "ownership_type", nullable = false, length = 10)
    private String ownershipType;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "status", nullable = false, length = 10)
    private String status = "ACTIVE";

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected Account() {
    }

public Account(
        String accountNumber,
        String accountType,
        String ownershipType
) {
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.ownershipType = ownershipType;
}

public Long getAccountId() {
    return accountId;
}

public String getAccountNumber() {
    return accountNumber;
}

public String getAccountType() {
    return accountType;
}

public String getOwnershipType() {
    return ownershipType;
}

public BigDecimal getBalance() {
    return balance;
}

public String getStatus() {
    return status;
}

public OffsetDateTime getCreatedAt() {
    return createdAt;
}

}
