package com.donggukthon.Showman.dto.map.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MapAllSnowmanResponse {
    private Long postingId;
    private Double latitude;
    private Double longitude;

    public static MapAllSnowmanResponse of(Long postingId, Double latitude, Double longitude) {
        return MapAllSnowmanResponse.builder()
                .postingId(postingId)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

}
