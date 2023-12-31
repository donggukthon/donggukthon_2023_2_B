package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.SocialType;
import com.donggukthon.Showman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /* 소셜로그인 */
    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
