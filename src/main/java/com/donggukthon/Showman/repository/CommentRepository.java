package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Comment;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPosting(Posting posting);
    List<Comment> findByUser(User user);
    Long countByPosting(Posting posting);
    Optional<Comment> findByUserAndAndPosting(User user, Posting posting);
}