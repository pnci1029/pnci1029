package com.example.firstproject.IOC;

public class Chef {
    private Foodfactory foodfactory;

    public Chef(Foodfactory foodfactory) {
        this.foodfactory = foodfactory;
    }

    public String cook(String menu) {
        //사용하는 재료를 넣어서 스트링 타입으로 재료명 반출
//        Pork pork = new Pork("맛있는 돼지");
//        Beef beef = new Beef("한우");
        Foodresult foodresult = foodfactory.get(menu);

        return foodresult.getName()+ "로 만든 "+menu;

    }
}
