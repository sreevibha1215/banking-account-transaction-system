
package com.banking.system.repository;

import com.banking.system.entity.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository
        extends JpaRepository<AccountHolder, Long> {
}
