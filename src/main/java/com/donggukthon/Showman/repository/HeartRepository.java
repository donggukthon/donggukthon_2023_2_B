package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Integer countByPostingPostingId(Long postingId);

}
