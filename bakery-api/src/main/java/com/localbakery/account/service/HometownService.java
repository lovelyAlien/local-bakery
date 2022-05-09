package com.localbakery.account.service;


import com.localbakery.account.domain.Account;
import com.localbakery.account.domain.Hometown;
import com.localbakery.account.repository.AccountRepository;
import com.localbakery.account.repository.HometownRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HometownService {

    @Autowired
    private final HometownRepository hometownRepository;

    @Autowired
    private final AccountRepository accountRepository;

    @Transactional
    public Long register(String userEmail, Point address) {
        Hometown hometown = new Hometown(address);

        hometownRepository.save(hometown);

        Account user = accountRepository.findByEmail(userEmail).orElse(null);

        user.setHometown(hometown);

        return hometown.getId();
    }
}
