package com.localbakery.account.repository;

import com.localbakery.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserName(String userName);

    Account findByEmail(String email);
}
