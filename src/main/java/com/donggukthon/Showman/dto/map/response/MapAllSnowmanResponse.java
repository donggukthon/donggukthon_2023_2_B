package com.donggukthon.Showman.dto.map.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MapAllSnowmanResponse {
    private Long postingId;
    private Double latitude;
    private Double longitude;

    public MapAllSnowmanResponse(Long postingId, Double latitude, Double longitude) {
        this.postingId = postingId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
