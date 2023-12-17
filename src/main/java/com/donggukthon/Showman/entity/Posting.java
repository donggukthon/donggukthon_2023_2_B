package com.donggukthon.Showman.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posting extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postingId;

    private String snowmanName;

    private String snowmanImageUrl;

    private String snowmanDescription;

    private Double latitude;

    private Double longitude;

    private String address;

    private String snowmanIdCardImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Posting(Long postingId, String snowmanName, String snowmanImageUrl, String snowmanDescription, Double latitude, Double longitude, String address, String snowmanIdCardImageUrl, User user){
        this.postingId = postingId;
        this.snowmanName = snowmanName;
        this.snowmanImageUrl = snowmanImageUrl;
        this.snowmanDescription = snowmanDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.snowmanIdCardImageUrl = snowmanIdCardImageUrl;
        this.user = user;
    }
}