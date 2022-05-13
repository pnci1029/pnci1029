package com.example.firstproject.service;

import com.example.firstproject.dto.AF;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.Articlerepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Service
public class Articleservice {
    @Autowired
    private Articlerepository articlerepository;

    public List<Article> index() {
        return articlerepository.findAll();
    }

    public Article show(Long ID) {
        return articlerepository.findById(ID).orElse(null);
    }

    public Article create(AF dto) {
        Article article = dto.toEntity();
        if(article.getID() != null)
            return null;
        return articlerepository.save(article);
    }

    public Article update(Long ID, AF dto) {    //05.13.02참고
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", ID, article.toString());

        Article target = articlerepository.findById(ID).orElse(null);
        if(target ==null || ID != article.getID()){
            log.info("잘못된 요청임니다 id: {} article: {}", ID, article.toString());
             return null;
        }
        target.patch(article);
        Article updated = articlerepository.save(target);
        return updated;
    }

    public Article delete(Long ID) {
        Article target=articlerepository.findById(ID).orElse(null);

        if(target == null){
            return null;
        }
        articlerepository.delete(target);
        return target;

    }

@Transactional
    public List<Article> createarticles(List<AF> dtos) {
        // dto 리스트를 entity 리스트로 변환
            List <Article> articlelist = dtos.stream()
                    .map(dto -> dto.toEntity())
                    .collect(Collectors.toList());

        // entity 리스트를 DB로 저장
        articlelist.stream()
                .forEach(article -> articlerepository.save(article));


        // 강제 예외 발생

        //결과값 반환
        return null;
    }
}
