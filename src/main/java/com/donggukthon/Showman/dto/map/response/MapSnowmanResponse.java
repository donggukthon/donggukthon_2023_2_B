package com.donggukthon.Showman.dto.map.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MapSnowmanResponse {
    private Long postingId;
    private LocalDateTime createdAt;
    private Double latitude;
    private Double longitude;
    private String snowmanName;
    private String snowmanImageUrl;
    private String address;

    public static MapSnowmanResponse of(Long postingId, LocalDateTime createdAt, Double latitude, Double longitude, String snowmanName, String snowmanImageUrl, String address) {
        return MapSnowmanResponse.builder()
                .postingId(postingId)
                .createdAt(createdAt)
                .latitude(latitude)
                .longitude(longitude)
                .snowmanName(snowmanName)
                .snowmanImageUrl(snowmanImageUrl)
                .address(address)
                .build();
    }
}
