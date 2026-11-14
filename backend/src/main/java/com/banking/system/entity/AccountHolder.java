
package com.banking.system.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_holders")
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_id")
    private Long accountHolderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "holder_role", nullable = false, length = 10)
    private String holderRole;

    @Column(name = "consent_given", nullable = false)
    private Boolean consentGiven = false;

    @Column(name = "created_at", nullable = false,
            insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected AccountHolder() {
    }

public AccountHolder(
        Account account,
        Customer customer,
        String holderRole,
        Boolean consentGiven
) {
    this.account = account;
    this.customer = customer;
    this.holderRole = holderRole;
    this.consentGiven = consentGiven;
}

public Long getAccountHolderId() {
    return accountHolderId;
}

public Account getAccount() {
    return account;
}

public Customer getCustomer() {
    return customer;
}

public String getHolderRole() {
    return holderRole;
}

public Boolean getConsentGiven() {
    return consentGiven;
}

public OffsetDateTime getCreatedAt() {
    return createdAt;
}

}
