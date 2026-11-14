
package com.banking.system.repository;

import com.banking.system.entity.Account;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndFindAccount() {

        Account account = new Account(
                "TESTACC1001",
                "SAVINGS",
                "INDIVIDUAL"
        );

        Account savedAccount = accountRepository.save(account);

        assertNotNull(savedAccount.getAccountId());

        entityManager.flush();
        entityManager.clear();

        Account foundAccount = accountRepository
                .findById(savedAccount.getAccountId())
                .orElseThrow();

        assertEquals("TESTACC1001", foundAccount.getAccountNumber());
        assertEquals("SAVINGS", foundAccount.getAccountType());
        assertEquals("INDIVIDUAL", foundAccount.getOwnershipType());
        assertEquals(0, new BigDecimal("0.00")
                .compareTo(foundAccount.getBalance()));
        assertEquals("ACTIVE", foundAccount.getStatus());
    }
}
