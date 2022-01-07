package com.localbakery.service;

import com.localbakery.domain.Account;
import com.localbakery.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public AccountMapper mapper;

    public Account saveAccount(Account account) {
        Account newAccount = new Account(account.getEmail(), account.getUserName());

        mapper.saveAccount(newAccount);
        return newAccount;
    }

    public Account findByEmail(String email) {
        return mapper.findByEmail(email);
    }

}