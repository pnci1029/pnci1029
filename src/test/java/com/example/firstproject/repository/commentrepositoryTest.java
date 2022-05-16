package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class commentrepositoryTest {

    @Autowired
    commentrepository commentrepository;

    @Test
    @DisplayName("특정 게시글의 모든댓글 조회")
    void findbyarticleID() {
        // 4번 게시글의 모든 댓글 조회하기
        {
            //입력데이터 준비
            Long articleID = 4L;

            // 실제
            List<comment> comments = commentrepository.findbyarticleID(articleID);

            // 예상
            Article article = new Article(4L, "당신의 취미는?", "댓글1");
            comment a = new comment(1L, article, "Kim", "숨쉬기");
            comment b = new comment(2L, article, "Lee", "잠자기");
            comment c = new comment(3L, article, "Choi", "숨쉬기2");
            List<comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 게시글의 모든 댓글 출력!");
        }
        // 1번 게시글의 모든 글 조회하기
        {
            // 입력 데이터 조회
            Long articleID = 1L;

            // 실제데이터 조회
            List<comment> comments = commentrepository.findbyarticleID(articleID);

            //예상데이터 조회
            Article article = new Article(1L, "가가가가", "1111");
            List<comment> expected = Arrays.asList();

            
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }

        // 9번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 조회
            Long articleID = 9L;

            // 실제 데이터 조회
            List<comment> comments = commentrepository.findbyarticleID(articleID);

            //예상데이터 조회
            Article article = new Article(9L, null, null);
            List<comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString());

        }
        // 9999번째 게시글의 모든 댓글 조회
        {
            // 입력 데이터 조회

            // 실제 데이터 조회

            //예상데이터 조회

            // 검증

        }

        // -1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 조회
            Long articleID  = -1L;

            // 실제 데이터 조회
            List<comment> comments = commentrepository.findbyarticleID(articleID);

            //예상데이터 조회
            Article article = new Article(-1L, null, null);
            List<Article> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString());

        }

    }


    @Test
    @DisplayName("특정 닉네임의 모든 게시글 조회")
    void findbynickname() {
        {   // Kim 닉네임의 게시글 조회
            // 입력 데이터 조회
            String nickname = "Kim";

            // 실제 데이터
            List<comment> comments =  commentrepository.findbynickname(nickname);

            // 예상 데이터
            comment a = new comment(1L,new Article(4L, "당신의 취미는?", "댓글1"), nickname, "숨쉬기");
            comment b = new comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글2"), nickname, "죽");
            List<comment> expected = Arrays.asList(a, b);


            // 검증
            assertEquals(expected.toString(), comments.toString());
        }

    }
}