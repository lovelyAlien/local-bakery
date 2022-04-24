package com.localbakery.account.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hometown")
public class Hometown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hometownId")
    private Long id;

    @Column(name= "location")
    private Point address;

    public Hometown(Point address) {
        this.address = address;
    }
}
