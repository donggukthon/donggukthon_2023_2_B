package com.donggukthon.Showman.service.map;

import com.donggukthon.Showman.dto.map.response.MapAllSnowmanResponse;
import com.donggukthon.Showman.dto.map.response.MapAroundSnowmanResponse;
import com.donggukthon.Showman.dto.map.response.MapSnowmanResponse;

import java.util.List;

public interface MapService {

    List<MapAllSnowmanResponse> getAllSnowman();

    MapSnowmanResponse getSnowman(Long postingId);

    List<MapAroundSnowmanResponse> getAroundSnowman(Long postingId);
}
