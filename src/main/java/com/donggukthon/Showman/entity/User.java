package com.donggukthon.Showman.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String nickname;

    private String password;

    private String profileImageUrl;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public User(Long userId, String nickname, String password, String profileImageUrl, String socialId, SocialType socialType){
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.socialId = socialId;
        this.socialType = socialType;
    }
}
