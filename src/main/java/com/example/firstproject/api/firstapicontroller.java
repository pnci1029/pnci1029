package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // 이전에는 @Controller 사용 이번에는 json으로 반환을 위해 restAPI용 컨트롤러 생성
public class firstapicontroller {

    @GetMapping("/api/hello")
    public String hello(){

        return "helloWorld";
    }
}
