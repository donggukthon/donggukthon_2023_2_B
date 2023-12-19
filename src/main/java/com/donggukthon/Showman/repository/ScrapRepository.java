package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Scrap;
import com.donggukthon.Showman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findByUser(User user);
}
