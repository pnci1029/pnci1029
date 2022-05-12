package com.example.firstproject.controller;

import com.example.firstproject.dto.AF;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.Articlerepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class articlecontroller {
    @Autowired
    private Articlerepository articlearticlerepository;

    @GetMapping("/articles/new")
    public String newarticle(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createarticle(AF form){

        log.info(form.toString());
        // System.out.println(form.toString());   =>Systemln 안쓰고 로깅으로 대체!   아래에도 마찬가지

        // DTO 를 Entity로 전환
        Article article =  form.toEntity();  //article이라는 Entity 클래스에 form.toEntity 메소드 정의
        log.info(article.toString());
        //System.out.println(article.toString());


        // Entity(의 클래스 article) 를 Repositive로 DB에 저장!
        Article saved = articlearticlerepository.save(article);
        log.info(saved.toString());
        // System.out.println(saved.toString());

        return "";
    }

    @GetMapping("/articles/{ID}")
    public String show(@PathVariable Long ID, Model model){
        log.info("ID = "+ID);
        // 리파지토리를 통해 id를 가져옴
       Article articleEntity = articlearticlerepository.findById(ID).orElse(null);

       //가져온 id를 모델로 등록
       model.addAttribute("article", articleEntity);

       //보여줄 페이지 설정
        return "articles/show";
    }


}
