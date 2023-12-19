package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    @Query(value = "select * from posting\n" +
            "where latitude between :startX and :endX and longitude between :startY and :endY ;",
            nativeQuery = true)
    List<Posting> findWithinMap(@Param("startX") Double startX,
                                @Param("endX") Double endX,
                                @Param("startY") Double startY,
                                @Param("endY") Double endY);

    @Query(value = "select *, (6371*acos(cos(radians(latitude))*cos(radians(:curMemberX))*cos(radians(:curMemberY)-radians(longitude))\n" +
            "+sin(radians(latitude))*sin(radians(:curMemberX)))) AS distance\n" +
            "from posting\n" +
            "where latitude between :startX and :endX and longitude between :startY and :endY " +
            "having distance <= 10\n" +
            "order by distance DESC;",
            nativeQuery = true)
    List<Posting> findWithinRadius(@Param("curMemberX") Double curMemberX,
                                   @Param("curMemberY") Double curMemberY,
                                   @Param("startX") Double startX,
                                   @Param("endX") Double endX,
                                   @Param("startY") Double startY,
                                   @Param("endY") Double endY);

}
