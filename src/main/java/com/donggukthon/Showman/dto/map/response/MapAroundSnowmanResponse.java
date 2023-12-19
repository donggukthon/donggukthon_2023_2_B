package com.donggukthon.Showman.dto.map.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MapAroundSnowmanResponse {

    private Long postingId;
    private String snowmanName;
    private String snowmanImageUrl;
    private LocalDateTime createdAt;
    private String address;
    private Double latitude;
    private Double longitude;

    public static MapAroundSnowmanResponse of(Long postingId, String snowmanName, String snowmanImageUrl, LocalDateTime createdAt, String address, Double latitude, Double longitude) {
        return MapAroundSnowmanResponse.builder()
                .postingId(postingId)
                .snowmanName(snowmanName)
                .snowmanImageUrl(snowmanImageUrl)
                .createdAt(createdAt)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
