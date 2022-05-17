package com.example.firstproject.entity;

import com.example.firstproject.dto.commentdto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "article_ID")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;


    public static comment createcomment(commentdto dto, Article article) {
        //예외 발생
        if (dto.getID() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 없어야 합니다.");
        }
        if (dto.getArticleID() != article.getID()) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID와 목록의 ID가 다릅니다.");
        }


        //엔티티 생성 및 반환
        return new comment(
                dto.getID(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(commentdto dto) {
        // 예외발생
        if(this.ID != dto.getID()){
            throw new IllegalArgumentException("아이디가 다릅니다.");
        }
        if(dto.getNickname() != null){
            this.nickname = getNickname();
        }
        if(dto.getBody() !=null){
            this.body = dto.getBody();
        }

        //or 객체 갱신

    }
}
