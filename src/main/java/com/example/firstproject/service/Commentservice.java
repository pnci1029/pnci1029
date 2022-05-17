package com.example.firstproject.service;

import com.example.firstproject.dto.commentdto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.comment;
import com.example.firstproject.repository.Articlerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Commentservice {

    @Autowired
    private com.example.firstproject.repository.commentrepository commentrepository;

    @Autowired
    private Articlerepository articlerepository;

    public List<commentdto> comments(Long articleID) {
        // 댓글목록 조회
//        List<comment> comments = commentrepository.findbyarticleID(articleID);
//
//        //엔티티 -> dto로 변환
//        List<commentdto> dtos= new ArrayList<>();
//        for(int i = 0; i <comments.size();  i ++){
//            comment c = comments.get(i);
//            commentdto dto = commentdto.createdto(c);
//            dtos.add(dto);
//        }
                //위 아래 같은 내용 스트림 문법 공부하기

        // 변환된 값 반환
        return commentrepository.findbyarticleID(articleID)
                .stream()
                .map(comment -> commentdto.createdto(comment))
                .collect(Collectors.toList());

    }
    @Transactional
    public commentdto create(long articleID, commentdto dto) {
            // 게시글 조회 및 예외처리
            Article article = articlerepository.findById(articleID)
                    .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

            // 엔티티로 생성
            comment Comment = comment.createcomment(dto, article);

            // db에 저장
            comment created =commentrepository.save(Comment);

            //dto로 변환하여 반환

            return  commentdto.createdto(created);
    }
    @Transactional
    public commentdto edit(Long ID, commentdto dto) {
        // 댓글 조회  + 예외 발생
       comment target =  commentrepository.findById(ID)
               .orElseThrow(() -> new IllegalArgumentException("수정할 댓글이 없습니다."));

        // 댓글 수정   patch 메소드는 임의로 만든것
        target.patch(dto);

        // db에 저장
         comment edited= commentrepository.save(target);

         //댓글 엔티티를 dto로 변환 및 반환
        return commentdto.createdto(edited);
    }
@Transactional
    public commentdto delete(Long ID) {
        // 댓글 조회 + 예외 발생
        comment target = commentrepository.findById(ID)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다."));

        // 댓글 삭제
        commentrepository.delete(target);

        //삭제 댓글 dto로 반환
        return commentdto.createdto(target);

    }
}
