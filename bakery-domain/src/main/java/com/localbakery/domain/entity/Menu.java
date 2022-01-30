package com.localbakery.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store_menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeMenuId")
    private Long storeMenuId;

    @Column(name = "storeId")
    private Long storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "signature", columnDefinition = "TINYINT")
    private Boolean signature;


    @Column(name = "modifiedBy")
    private String modifiedBy;

    @Column(name = "modifiedAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
