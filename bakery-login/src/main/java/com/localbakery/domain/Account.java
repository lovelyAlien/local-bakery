package com.localbakery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    private String email;

    private String userName;

    public Account(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

}
