package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Heart;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Long countByHeart(Posting posting);
    List<Heart> findByUser(User user);
}
