package com.localbakery.account.repository;

import com.localbakery.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserName(String userName);

    Optional<Account> findByEmail(String email);
}
