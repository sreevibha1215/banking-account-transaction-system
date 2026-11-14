
package com.banking.system.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "customer_type", nullable = false, length = 10)
    private String customerType;

    @Column(name = "kyc_status", nullable = false, length = 20)
    private String kycStatus = "PENDING";

    @Column(
        name = "created_at",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private OffsetDateTime createdAt;

    // Required by JPA
    protected Customer() {
    }

    // Constructor for creating a new customer
    public Customer(
            String fullName,
            String email,
            String phoneNumber,
            String customerType
    ) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
    }

    // Getter methods

    public Long getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    // Update customer KYC status
    public void updateKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }
}
