package com.localbakery.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private Long storeId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId")
    private List<Menu> menus;

//    @OneToMany(mappedBy = "store")
//    private List<Review> reviews=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private storeType type;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name= "ratingAverage")
    private float ratingAverage;

    @Column(name= "totalCount")
    private int totalCount;

    @Column(name= "totalRating")
    private float totalRating;

    @Column(name = "homePageUrl")
    private String homePageUrl;

    @Column(name = "thumbnailImageUrl")
    private String thumbnailImageUrl;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "startTime", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "endTime", columnDefinition = "TIME")
    private LocalTime endTime;

    @Column(name = "location", columnDefinition = "POINT")
    private Point location;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    @Column(name = "modifiedAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;


    public enum storeType {
        BAKERY
    }


    public void rating(float rating){
        this.totalCount+=1;
        this.totalRating+=rating;
        this.ratingAverage=(float)(this.totalRating/this.totalCount);
    }

    public void updateRating(float before_rating, float after_rating){
        this.totalRating=this.totalRating-before_rating+after_rating;
        this.ratingAverage=(float)(this.totalRating/this.totalCount);
    }

    public void deleteRating(float rating){
        this.totalCount-=1;
        this.totalRating-=rating;

        if(totalCount==0)
            this.ratingAverage=0;
        else
            this.ratingAverage=(float)(this.totalRating/this.totalCount);
    }
}
