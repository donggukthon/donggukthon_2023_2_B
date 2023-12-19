package com.donggukthon.Showman.controller;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.dto.map.response.MapAllSnowmanResponse;
import com.donggukthon.Showman.dto.map.response.MapAroundSnowmanResponse;
import com.donggukthon.Showman.dto.map.response.MapSnowmanResponse;
import com.donggukthon.Showman.service.map.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MapController {

    private final MapService mapService;

    // 지도 조회(모든 눈사람 핀 조회)
    @GetMapping("/map")
    public CommonResponse<List<MapAllSnowmanResponse>> getAllSnowman() {
        return CommonResponse.success(mapService.getAllSnowman());
    }

    // 특정 눈사람 핀 선택시 바텀시트 조회
    @GetMapping("/map/{postingId}")
    public CommonResponse<MapSnowmanResponse> getSnowman(@PathVariable Long postingId) {
        return CommonResponse.success(mapService.getSnowman(postingId));
    }

    // 특정 눈사람 기준 10km 반경의 주변 눈사람 명합 띄우는 바텀시트 조회
    @GetMapping("/map/{postingId}/around")
    public CommonResponse<List<MapAroundSnowmanResponse>> getAroundSnowman(@PathVariable Long postingId) {
        return CommonResponse.success(mapService.getAroundSnowman(postingId));
    }
}
