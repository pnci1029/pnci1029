package com.example.firstproject.IOC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {

    @Test
    void 돈까스를_만들자(){
        Foodfactory foodfactory = new Foodfactory();
        Chef chef = new Chef(foodfactory);
        String menu = "돈까스";

        String food = chef.cook(menu);

        String expected = "맛있는 돼지로 만든 돈까스";

        assertEquals(expected, food);
        System.out.println(food);
    }
    @Test
    void 스테이크를_만들자(){
        Foodfactory foodfactory = new Foodfactory();
        Chef chef = new Chef(foodfactory);
        String menu = "스테이크";

        String food = chef.cook(menu);

        String expected = "한우로 만든 스테이크";

        assertEquals(expected, food);
        System.out.println(food);

    }

    @Test
    void 치킨을_만들자(){
        Foodfactory foodfactory = new Foodfactory();
        Chef chef = new Chef(foodfactory);
        String menu = "치킨";

        String food = chef.cook(menu);

        String expected = "브라질산 닭로 만든 치킨";

        assertEquals(expected, food);
        System.out.println(food);
    }


}