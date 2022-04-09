package com.localbakery.authentication.service;

import com.localbakery.account.domain.Account;
import com.localbakery.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AccountService {

    public final AccountRepository accountRepository;
    public Account saveAccount(Account account) {
        Account newAccount = new Account(account.getEmail(), account.getUserName());
        return accountRepository.save(newAccount);
    }

    public Optional<Account> findByEmail(String email) {
        return Optional.ofNullable(accountRepository.findByEmail(email));
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }
}