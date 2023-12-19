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

    public MapSnowmanResponse(Long postingId, LocalDateTime createdAt, Double latitude, Double longitude, String snowmanName, String snowmanImageUrl) {
        this.postingId = postingId;
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.snowmanName = snowmanName;
        this.snowmanImageUrl = snowmanImageUrl;
    }
}
