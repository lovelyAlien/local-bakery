package com.localbakery.authentication.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.localbakery.account.domain.Account;
import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.authentication.oauth2.AppProperties;
import com.localbakery.authentication.oauth2.TokenProvider;
import com.localbakery.authentication.security.CustomUserDetailsService;
import com.localbakery.authentication.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bakery")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${google.oauth.client-id}")
    private String OIDC_CLIENT_ID;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AppProperties appProperties;


    @PostMapping("/login")
    public ResponseContainer<String> login(@RequestParam("token") String token) throws IOException, GeneralSecurityException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory()).setAudience(Collections.singletonList(OIDC_CLIENT_ID)).build();

        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);

        String responseMessage;

        if (verifier.verify(idToken)) {
            responseMessage = String.valueOf(idToken);

            GoogleIdToken.Payload payload = idToken.getPayload();

            Long userId = tokenProvider.getUserIdFromToken(String.valueOf(idToken));

            Account newUser = new Account(payload.getEmail(), (String) payload.get("name"));
            accountService.saveAccount(newUser);

            UserDetails userDetails = customUserDetailsService.loadUserById(userId);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            responseMessage = "id token verification failed";
        }
        return ResponseContainer.<String>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(responseMessage)
                .build();
    }
}
