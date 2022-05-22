package com.example.firstproject.IOC;

import jdk.nashorn.internal.objects.annotations.Constructor;


public class Foodfactory {


    public Foodresult get(String menu) {
        switch (menu){
            case "돈까스":
                return new Pork("맛있는 돼지");
            case "스테이크":
                return new Beef("한우");
            case "치킨":
                return new Chicken("브라질산 닭");
            default:
                return null;
        }
    }
}

