package com.localbakery.account;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("/sign-up")
    public ResponseEntity<Void> signUpForm() {


        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/restricted")
    public ResponseEntity<Void> restricted() {
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<Void> signUp() {

        System.out.println("hello");


        return new ResponseEntity(HttpStatus.OK);
    }

}
