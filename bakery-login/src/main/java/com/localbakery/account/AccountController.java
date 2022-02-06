package com.localbakery.account;


import com.localbakery.payload.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("/")
    public ResponseEntity<?> login(@RequestParam String token) {

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
