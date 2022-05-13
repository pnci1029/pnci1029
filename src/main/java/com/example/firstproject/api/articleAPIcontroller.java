package com.example.firstproject.api;

import com.example.firstproject.dto.AF;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.Articlerepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // 이전에는 @Controller 사용 이번에는 json으로 반환을 위해 restAPI용 컨트롤러 생성
               // 일반 컨트롤러 -> 뷰 템플릿 페이지 반환 (return "url을 위한 머스태치 이름")
                 // rest컨트롤러 -> 일반적인 json이나 데이터 반환가능
@Slf4j
public class articleAPIcontroller {
    @Autowired
    private Articlerepository articlerepository;

    //get
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articlerepository.findAll();
    }

    @GetMapping("/api/articles/{ID}")    // 주의) url에 ID 소문자 쓰면 달라서 에러남
    public Article index(@PathVariable Long ID) {
        return articlerepository.findById(ID).orElse(null);
    }

    // post
    @PostMapping("/api/articles")
    public Article create(@RequestBody AF dto){

        Article article = dto.toEntity();
        return articlerepository.save(article);
    }


    //patch
    @PatchMapping("/api/articles/{ID}")
    public ResponseEntity<Article> update(@PathVariable Long ID, @RequestBody AF dto){
         // 수정용 엔티티 생성
        Article article =dto.toEntity();
        log.info("ID: {}, article: {}", ID, article.toString());    //로그 오류 발생시 @Slf4j입력

        // DB에서 ID 를 찾아서 조회
        Article target = articlerepository.findById(ID).orElse(null);

        // 잘못된 요청 확인(잘못된 url 입력)
        if(target == null || ID != article.getID()){
            log.info("잘못된 요청 임니다. ID: {}, article: {}", ID, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 아닐 시 요청 처리
         Article updated = articlerepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //delete
    @DeleteMapping("/api/articles/{ID}")
    public ResponseEntity<Article> delete(@PathVariable  Long ID){
        //지울 대상 찾기
        Article target = articlerepository.findById(ID).orElse(null);

            //target이 없는경우
            if(target == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

        //지우기
        articlerepository.delete(target);

        //데이터 반환

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }



}
