package com.example.firstproject.IOC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {

    @Test
    void 돈가스_요리하기(){
        // 준비
        Chef chef = new Chef();
        String menu  =  "돈까스";
        // 수행
        String food = chef.cook(menu);


        // 예상
        String expected = "맛있는 돈까스";

        //  검증
        assertEquals(expected,  food);
        System.out.println(food);


    }

}