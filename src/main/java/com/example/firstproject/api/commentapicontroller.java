package com.example.firstproject.api;

import com.example.firstproject.dto.commentdto;
import com.example.firstproject.service.Commentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class commentapicontroller {
    @Autowired
    private Commentservice commentservice;

    // 기능추기
    //1. 댓글 목록 조회
    @GetMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<List<commentdto>> comments(@PathVariable Long articleID){
        // 프로세스 서비스에게 요청
        List<commentdto> dtos =commentservice.comments(articleID);

        //결과 출력

        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }


    //2. 댓글 생성
    @PostMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<commentdto> create(@PathVariable long articleID, @RequestBody commentdto dto){   // dto에 글 생성 내용들 삽입
        commentdto createdto =commentservice.create(articleID, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdto);
    }

    //3. 댓글 수정
    @PatchMapping("/api/comments/{ID}")
    public ResponseEntity<commentdto> edit(@PathVariable Long ID, @RequestBody commentdto dto){
        // 서비스에게 위임
        commentdto editdto = commentservice.edit(ID, dto);

        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(editdto);
    }



    //4. 댓글 삭제
    @DeleteMapping("/api/comments/{ID}")
    public ResponseEntity<commentdto> delete(@PathVariable Long ID){
        // 서비스에 위임
        commentdto deleted = commentservice.delete(ID);

        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(deleted);

    }


}
