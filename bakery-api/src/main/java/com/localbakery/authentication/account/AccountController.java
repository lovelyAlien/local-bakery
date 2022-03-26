package com.localbakery.authentication.account;


import com.localbakery.authentication.payload.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @ApiIgnore
    @GetMapping("/")
    public ResponseEntity<?> login(@RequestParam String token) {
        StringBuilder sb = new StringBuilder();
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
