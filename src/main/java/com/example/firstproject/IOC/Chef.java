package com.example.firstproject.IOC;

public class Chef {


    public String cook(String menu) {
        //재료 준비
        Pork pork = new Pork("맛");

        //요리
        return pork.getName() + "있는 " + menu;
    }
}
