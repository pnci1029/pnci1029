package com.example.firstproject.dto;

import com.example.firstproject.entity.comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class commentdto {
            @JsonProperty("ID")
            private Long ID;
            @JsonProperty("article_ID")
            private Long articleID;
            private String nickname;
            private String body;

            public static commentdto createdto(comment c){
                return new commentdto(
                        c.getID(),
                        c.getArticle().getID(),
                        c.getNickname(),
                        c.getBody()

                );
            }


}
