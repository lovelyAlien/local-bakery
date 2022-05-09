package com.localbakery.authentication.controller;

import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.authentication.dto.IdTokenRequest;
import com.localbakery.authentication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bakery")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseContainer<Object> login(@RequestBody IdTokenRequest idToken,
                                           HttpServletResponse response) throws IOException, GeneralSecurityException, IllegalAccessException {
        String authToken = accountService.loginOAuthGoogle(idToken);
        Cookie cookie = new Cookie("AUTH-TOKEN", authToken);
        cookie.setMaxAge(7 * 24 * 3600);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseContainer.builder()
                .rCode("200")
                .rMessage("Login success")
                .rData(response)
                .build();
    }
}
