package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // 이전에는 @Controller 사용 이번에는 json으로 반환을 위해 restAPI용 컨트롤러 생성
                    // 일반 컨트롤러 -> 뷰 템플릿 페이지 반환 (return "url을 위한 머스태치 이름")
                    // rest컨트롤러 -> 일반적인 json이나 데이터 반환가능
public class firstapicontroller {

    @GetMapping("/api/hello")
    public String hello(){

        return "helloWorld";
    }
}
