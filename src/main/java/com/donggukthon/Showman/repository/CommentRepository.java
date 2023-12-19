package com.donggukthon.Showman.repository;

import com.donggukthon.Showman.entity.Comment;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPosting(Posting posting);
    List<Comment> findByUser(User user);
    Long countByPosting(Posting posting);
}