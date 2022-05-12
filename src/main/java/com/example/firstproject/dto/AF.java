package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class AF {

    private Long ID;
    private String title;
    private String content;





    public Article toEntity() {
        return new Article(ID, title, content);
    }
}
