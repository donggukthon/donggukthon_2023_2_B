package com.donggukthon.Showman.service.map;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.dto.map.response.MapAllSnowmanResponse;
import com.donggukthon.Showman.dto.map.response.MapAroundSnowmanResponse;
import com.donggukthon.Showman.dto.map.response.MapSnowmanResponse;
import com.donggukthon.Showman.entity.Direction;
import com.donggukthon.Showman.entity.Location;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.repository.PostingRepository;
import com.donggukthon.Showman.util.GeometryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService{

    private final PostingRepository postingRepository;
    @Override
    @Transactional(readOnly = true)
    public List<MapAllSnowmanResponse> getAllSnowman() {
        List<Posting> allSnowman = postingRepository.findAll();

        ArrayList<MapAllSnowmanResponse> mapAllSnowmanResponses = new ArrayList<>();

        for (Posting posting : allSnowman) {
            MapAllSnowmanResponse mapAllSnowmanResponse = MapAllSnowmanResponse.builder()
                    .postingId(allSnowman.get(0).getPostingId())
                    .latitude(allSnowman.get(0).getLatitude())
                    .longitude(allSnowman.get(0).getLongitude())
                    .build();
            mapAllSnowmanResponses.add(mapAllSnowmanResponse);
        }

        return mapAllSnowmanResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public MapSnowmanResponse getSnowman(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        return MapSnowmanResponse.builder()
                .postingId(posting.getPostingId())
                .longitude(posting.getLongitude())
                .latitude(posting.getLatitude())
                .snowmanName(posting.getSnowmanName())
                .snowmanImageUrl(posting.getSnowmanImageUrl())
                .createdAt(posting.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MapAroundSnowmanResponse> getAroundSnowman(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));

        Double x=posting.getLatitude();
        Double y=posting.getLongitude();

        // 현재 핀 기준으로 북동쪽과 남서쪽의 위치를 계산
        Location northEast = GeometryUtil.calculate(x, y, 10.0, Direction.NORTHEAST.getBearing());
        Location southWest = GeometryUtil.calculate(x, y, 10.0, Direction.SOUTHWEST.getBearing());

        List<Posting> postings = postingRepository.findInBoundingBoxOrderedByDistance(
                southWest.getLatitude(), northEast.getLatitude(),
                southWest.getLongitude(), northEast.getLongitude(),
                x, y);

        ArrayList<MapAroundSnowmanResponse> mapAroundSnowmanResponses = new ArrayList<>();

        for (Posting aroundPosting : postings) {
            MapAroundSnowmanResponse mapAroundSnowmanResponse = MapAroundSnowmanResponse.builder()
                    .postingId(aroundPosting.getPostingId())
                    .snowmanName(aroundPosting.getSnowmanName())
                    .snowmanImageUrl(aroundPosting.getSnowmanImageUrl())
                    .createdAt(aroundPosting.getCreatedAt())
                    .address(aroundPosting.getAddress())
                    .latitude(aroundPosting.getLatitude())
                    .longitude(aroundPosting.getLongitude())
                    .build();
            mapAroundSnowmanResponses.add(mapAroundSnowmanResponse);
        }

        return mapAroundSnowmanResponses;
    }
}
