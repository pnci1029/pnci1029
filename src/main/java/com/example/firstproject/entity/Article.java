package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@Entity
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //db가 아이디 자동생성
    private Long ID;
    @Column
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content !=null)
            this.content = article.content;
    }
}
