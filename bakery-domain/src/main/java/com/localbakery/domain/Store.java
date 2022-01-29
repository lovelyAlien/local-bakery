package com.localbakery.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private storeType type;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "startTime")
    private Long startTime;

    @Column(name = "endTime")
    private Long endTime;

    @Embedded
    private Coordinate coordinate;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    @Column(name = "modifiedAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;


    @Embeddable
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coordinate {
        @Column(name = "altitude")
        private String altitude;
        @Column(name = "longitude")
        private String longitude;

    }

    enum storeType {
        BAKERY
    }
}
