package com.example.firstproject.repository;


import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface commentrepository extends JpaRepository<comment, Long> {
    // 특정게시글의 모든 댓글조회    by 쿼리
    @Query(value = "Select * FROM comment WHERE article_ID = :articleID", nativeQuery = true)
        List<comment> findbyarticleID(Long articleID);

    // 특정 닉네임의 모든 댓글 조회   by xml
        List<comment> findbynickname(String nickname);

}
