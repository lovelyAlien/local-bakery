package com.localbakery.service;

import com.localbakery.domain.Account;
import com.localbakery.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MapperService {

    public AccountMapper mapper;

    public MapperService(AccountMapper mapper) {
        this.mapper = mapper;
    }

    public Account saveAccount(Account account) {

        Account newAccount = new Account(account.getEmail(), account.getUserName());
        mapper.saveAccount(account.getEmail(), account.getUserName());

        return newAccount;
    }

    public Account findByEmail(String email) {
        return mapper.findByEmail(email);
    }

}