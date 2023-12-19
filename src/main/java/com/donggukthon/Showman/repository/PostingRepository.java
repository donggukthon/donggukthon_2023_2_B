package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    @Query("SELECT p FROM Posting p WHERE p.latitude BETWEEN ?1 AND ?2 AND p.longitude BETWEEN ?3 AND ?4 ORDER BY FUNCTION('distance', p.latitude, p.longitude, ?5, ?6)")
    List<Posting> findInBoundingBoxOrderedByDistance(Double minLat, Double maxLat, Double minLon, Double maxLon, Double originLat, Double originLon);

}
