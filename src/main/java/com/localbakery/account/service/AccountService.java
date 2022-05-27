package com.localbakery.account.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.localbakery.account.domain.Account;
import com.localbakery.account.repository.AccountRepository;
import com.localbakery.authentication.dto.IdTokenRequest;
import com.localbakery.authentication.oauth2.UserPrincipal;
import com.localbakery.authentication.util.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements UserDetailsService {

    private final JwtUtils jwtUtils;

    private final GoogleIdTokenVerifier verifier;

    public final AccountRepository accountRepository;

    public AccountService(@Value("${google.oauth.client-id}") String clientId, AccountRepository accountRepository,
                       JwtUtils jwtUtils) {
        this.accountRepository = accountRepository;
        this.jwtUtils = jwtUtils;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public String loginOAuthGoogle(IdTokenRequest idToken) throws IllegalAccessException {
        Account user = verifyIdToken(idToken.getIdToken());
        if (user == null) {
            throw new IllegalAccessException();
        }
        user = createOrUpdateUser(user);
        return jwtUtils.createToken(user, false);
    }

    private Account createOrUpdateUser(Account user) {
        Account existingUser = accountRepository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null) {
            user.setRoles("ROLE_USER");
            accountRepository.save(user);
            return user;
        }
        existingUser.setUserName(user.getUserName());
        existingUser.setImageUrl(user.getImageUrl());
        accountRepository.save(existingUser);
        return existingUser;
    }

    private Account verifyIdToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");
            String email = payload.getEmail();
            return Account.builder()
                    .userName(lastName + firstName)
                    .email(email)
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
        Account user = findByEmail(email)
          .orElseThrow(() ->
            new UsernameNotFoundException("User not found with email : " + email)
          );

        return UserPrincipal.create(user);
    }
}