package com.example.firstproject.api;

import com.example.firstproject.dto.AF;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.Articlerepository;
import com.example.firstproject.service.Articleservice;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http2.HpackDecoder;
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
    private Articleservice articleservice;

    //get
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleservice.index();
    }

    @GetMapping("/api/articles/{ID}")    // 주의) url에 ID 소문자 쓰면 달라서 에러남
    public Article show(@PathVariable Long ID) {
        return articleservice.show(ID);
    }

    // post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody AF dto){
        Article created = articleservice.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //patch
    @PatchMapping("/api/articles/{ID}")
    public ResponseEntity<Article> update(@PathVariable Long ID, @RequestBody AF dto){
        Article updated = articleservice.update(ID, dto);

        return (updated != null) ?ResponseEntity.status(HttpStatus.OK).body(updated):
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //delete
    @DeleteMapping("/api/articles/{ID}")
    public ResponseEntity<Article> delete(@PathVariable  Long ID){
        Article deleted = articleservice.delete(ID);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted):
                                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
        // 트랜잭션 만들기

    @GetMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> trasactest(@RequestBody List<AF> dtos){

        List<Article> createlist = articleservice.createarticles(dtos);

    return (createlist != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createlist):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


}
