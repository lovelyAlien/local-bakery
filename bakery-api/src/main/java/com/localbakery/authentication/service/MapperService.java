package com.localbakery.authentication.service;

import com.localbakery.authentication.domain.Account;
import com.localbakery.authentication.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MapperService {

    public AccountMapper mapper;

    public MapperService(AccountMapper mapper) {
        this.mapper = mapper;
    }

    public Account saveAccount(Account account) {

        Account newAccount = new Account(account.getEmail(), account.getUserName());
        mapper.saveAccount(newAccount);

        return newAccount;
    }

    public Optional<Account> findByEmail(String email) {
        return Optional.ofNullable(mapper.findByEmail(email));
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(mapper.findById(id));
    }
}