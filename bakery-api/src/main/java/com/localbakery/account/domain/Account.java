package com.localbakery.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private Long id;

    private String email;

    private String userName;

    private String imageUrl;

    @NotBlank
    @JsonIgnore
    private String roles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hometownId")
    private Hometown hometown;
}
