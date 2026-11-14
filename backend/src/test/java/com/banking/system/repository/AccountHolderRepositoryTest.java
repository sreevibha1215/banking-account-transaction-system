
package com.banking.system.repository;

import com.banking.system.entity.Account;
import com.banking.system.entity.AccountHolder;
import com.banking.system.entity.Customer;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountHolderRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndFindAccountHolder() {

        Customer customer = customerRepository.save(
                new Customer(
                        "Test Account Holder",
                        "account.holder.test@example.com",
                        "9876543210",
                        "RESIDENT"
                )
        );

        Account account = accountRepository.save(
                new Account(
                        "TESTHOLDER1001",
                        "SAVINGS",
                        "INDIVIDUAL"
                )
        );

        AccountHolder holder = accountHolderRepository.save(
                new AccountHolder(
                        account,
                        customer,
                        "PRIMARY",
                        false
                )
        );

        assertNotNull(holder.getAccountHolderId());

        entityManager.flush();
        entityManager.clear();

        AccountHolder foundHolder = accountHolderRepository
                .findById(holder.getAccountHolderId())
                .orElseThrow();

        assertEquals("PRIMARY", foundHolder.getHolderRole());
        assertFalse(foundHolder.getConsentGiven());

        assertEquals(
                account.getAccountId(),
                foundHolder.getAccount().getAccountId()
        );

        assertEquals(
                customer.getCustomerId(),
                foundHolder.getCustomer().getCustomerId()
        );
    }
}
