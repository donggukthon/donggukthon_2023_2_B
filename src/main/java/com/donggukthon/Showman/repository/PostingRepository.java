package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    Long countByUser(User user);
    List<Posting> findByUser(User user);
}
