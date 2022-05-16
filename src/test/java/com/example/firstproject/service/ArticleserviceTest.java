package com.example.firstproject.service;

import com.example.firstproject.dto.AF;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleserviceTest {

    @Autowired Articleservice articleservice;

    @Test
    void index() {
        // 예상 시나리오
        Article a = new Article(1L, "가가가", "1111");
        Article b = new Article(2L, "나나나", "2222");
        Article c = new Article(3L, "다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제 시나리오
        List<Article> articles =articleservice.index();


        // 예상 실제 시나리오 비교 검증
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_success() { // 실존하는 id 검색
        //예상
        Long ID = 1L;
        Article expected =new Article(1L, "가가가", "1111");

        // 실제
        Article article = articleservice.show(ID);

        // 비교
        assertEquals(expected.toString(), article.toString());

    }
    @Test
    void show_failed(){ // 실존하지 않는 id 검색

        // 예상
        Long ID = 5L;
        Article expected =null;

        // 실제
        Article article = articleservice.show(ID);


        // 비교
        assertEquals(expected, article);

    }


    @Test
    void create_success() {  // title과 content가 있는 dto 출력
        // 예상
        String title = "라라라";
        String content = "4444";
        AF dto = new AF(null, content, title);
        Article expected = new Article(4L, content, title);

        // 실제
        Article articles = articleservice.create(dto);

        // 비교
        assertEquals(expected.toString(), articles.toString());

    }


    @Test
    void create_failed(){  // ID 나 content등 값을 비웠을 경우
        // 예상
        Long ID = 1L;
        String title = "가가가";
        String content = "1111";
        AF dto = new AF(ID, title, content);
        Article expected = null;

        // 실제
         Article article = articleservice.create(dto);

        // 비교
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void update_success_01() {   // 존재하는 ID와 title과  content가 있는 dto 입력
        // 예상
        Long ID = 3L;
        String title = "라라라";
        String content = "4444";
        AF dto = new AF(ID, title, content);
        Article expected = new Article(ID, title, content);

        // 실제
        Article article = articleservice.update(ID,dto);


        // 비교
        assertEquals(expected.toString(), article.toString());

    }
    @Test
    @Transactional
    void update_success_02(){ // 존재하는 ID와 title만 있는 dto 입력
        // 예상
        Long ID = 1L;
        String title = "다다다";
        String content = null;
        AF dto = new AF(ID, title, content);
        Article expected = new Article(ID, title, "1111");


        // 실제
        Article  article =articleservice.update(ID, dto);


        // 비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    @Transactional
    void update_failed_01(){   // 존재하지않는 ID와 dto 입력
        // 예상
        Long ID = 4L;
        String title;
        String content;
        AF dto = new AF(ID, "마마마마", "6666");
        Article expected = null;

        // 실제
        Article article = articleservice.update(ID, dto);

        // 비교
        assertEquals(expected, article);


    }


    @Test
    @Transactional
    void delete_success() {     //존재하는 ID 입력
        //예상
        Long ID = 3L;
        Article expected = new Article(3L, "다다다", "3333");

        // 실제
        Article article = articleservice.delete(ID);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }


    @Test
    @Transactional
    void delete_failed(){       // 존재하지 않는 ID 입력
        // 예상
        Long ID = 4L;
        Article expected = null;

        // 실제
        Article article = articleservice.delete(ID);

        // 비교
        assertEquals(expected, article);

    }
}