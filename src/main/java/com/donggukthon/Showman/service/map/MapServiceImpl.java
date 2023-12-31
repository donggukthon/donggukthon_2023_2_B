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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapServiceImpl implements MapService{

    private final PostingRepository postingRepository;


    // 완전하게 포스팅 된 눈사람이 아닌 경우에는 조회에서 걸러야함 = 위도, 경도가 없는 포스팅은 조회에서 걸러야함
    @Override
    @Transactional(readOnly = true)
    public List<MapAllSnowmanResponse> getAllSnowman() {
        List<Posting> allSnowman = postingRepository.findByLatitudeIsNotNullAndLongitudeIsNotNull();

        ArrayList<MapAllSnowmanResponse> mapAllSnowmanResponses = new ArrayList<>();

        for (Posting posting : allSnowman) {
            MapAllSnowmanResponse mapAllSnowmanResponse = MapAllSnowmanResponse.of(posting.getPostingId(), posting.getLatitude(), posting.getLongitude());
            mapAllSnowmanResponses.add(mapAllSnowmanResponse);
        }

        return mapAllSnowmanResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public MapSnowmanResponse getSnowman(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new CustomException(Result.NOT_FOUND_POSTING));
        return MapSnowmanResponse.of(posting.getPostingId(), posting.getCreatedAt(), posting.getLatitude(), posting.getLongitude(), posting.getSnowmanName(), posting.getSnowmanImageUrl(), posting.getAddress());
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
        log.info("start");

        List<Posting> postings = postingRepository.findWithinRadius(x, y, southWest.getLatitude(), northEast.getLatitude(), southWest.getLongitude(), northEast.getLongitude());
        ArrayList<MapAroundSnowmanResponse> mapAroundSnowmanResponses = new ArrayList<>();

        for (Posting post : postings) {
            if (post.getPostingId()==postingId) {
                continue;
            }else {
                MapAroundSnowmanResponse mapAroundSnowmanResponse = MapAroundSnowmanResponse.of(post.getPostingId(), post.getSnowmanName(), post.getSnowmanImageUrl(), post.getCreatedAt(), post.getAddress(), post.getLatitude(), post.getLongitude());
                mapAroundSnowmanResponses.add(mapAroundSnowmanResponse);
            }
        }
        mapAroundSnowmanResponses.add(0, MapAroundSnowmanResponse.of(posting.getPostingId(), posting.getSnowmanName(), posting.getSnowmanImageUrl(), posting.getCreatedAt(), posting.getAddress(), posting.getLatitude(), posting.getLongitude()));

        return mapAroundSnowmanResponses;
    }
}
