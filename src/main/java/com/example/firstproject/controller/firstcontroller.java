package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class firstcontroller {
    @GetMapping("/hi")
    public String sayhi(Model model){

        model.addAttribute("username", "하이");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeyou(Model model){
        model.addAttribute("username", "잘가");
        return "bye";
    }
}
