
package com.banking.system.repository;

import com.banking.system.entity.Customer;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndFindCustomer() {

        Customer customer = new Customer(
                "Test Customer",
                "customer.integration.test@example.com",
                "9876543210",
                "RESIDENT"
        );

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getCustomerId());

        // Write pending changes to PostgreSQL
        entityManager.flush();

        // Remove cached entities from Hibernate
        entityManager.clear();

        // Retrieve the customer from PostgreSQL
        Customer foundCustomer = customerRepository
                .findById(savedCustomer.getCustomerId())
                .orElseThrow();

        assertEquals("Test Customer", foundCustomer.getFullName());
        assertEquals("RESIDENT", foundCustomer.getCustomerType());
        assertEquals("PENDING", foundCustomer.getKycStatus());
    }
}
