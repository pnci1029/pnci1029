package com.example.firstproject.controller;

import com.example.firstproject.dto.AF;
import com.example.firstproject.dto.commentdto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.Articlerepository;
import com.example.firstproject.service.Commentservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class articlecontroller {
    @Autowired
    private Articlerepository articlearticlerepository;
    @Autowired
    private Commentservice commentservice;

    @GetMapping("/articles/new")
    public String newarticle(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createarticle(AF form){

        log.info(form.toString());
        // System.out.println(form.toString());   =>Systemln 안쓰고 로깅으로 대체!   아래에도 마찬가지

        // DTO 를 Entity로 전환
        Article article =  form.toEntity();  //article이라는 Entity 클래스에 form.toEntity 메소드 정의
        log.info(article.toString());
        //System.out.println(article.toString());


        // Entity(의 클래스 article) 를 Repositive로 DB에 저장!
        Article saved = articlearticlerepository.save(article);
        log.info(saved.toString());
        // System.out.println(saved.toString());

        return "redirect:/articles/"+ saved.getID();
    }

    @GetMapping("/articles/{ID}")
    public String show(@PathVariable Long ID, Model model){
        log.info("ID = "+ID);
        // 리파지토리를 통해 id를 가져옴
       Article articleEntity = articlearticlerepository.findById(ID).orElse(null);
       List<commentdto> commentdtos = commentservice.comments(ID);

       //가져온 id를 모델로 등록
       model.addAttribute("article", articleEntity);
       model.addAttribute("commentdtos", commentdtos);

       //보여줄 페이지 설정
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        // 모든 article을 보여준다
        List<Article> articleEntityList = articlearticlerepository.findAll();

        //가져온 article을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);

        // 뷰 페이지 설정

        return "articles/index";
    }
    @GetMapping("/articles/{ID}/edit")
    public String edit(@PathVariable Long ID, Model model){
        //수정할 데이터 가져오기(DB)
        Article articleEntity = articlearticlerepository.findById(ID).orElse(null);

        //모델에 데이터 등록
        model.addAttribute("article" ,articleEntity);
        //뷰 페이지 설정
        return "articles/edit";
        }

    @PostMapping("/articles/update")
    public String update(AF form){
        log.info(form.toString()); // 로그로 확인함으로써 업데이트된 내용 추출확인 완료

        // 확인 후 dto -> DB로 데이터 보내기
        // dto -> Entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());   // Entity로 변환이 됐는지 확인 출력


        // Entity -> repository(DB)
            //기존데이터 가져오기
        Article target = articlearticlerepository.findById(articleEntity.getID()).orElse(null);
        //타겟이라는 변수는         여기(DB)에서           아이디를 찾아 데이터를 가져오는데, 없을 시 널 리턴

            //기존데이터값 수정(갱신)
        if(target != null){
            articlearticlerepository.save(articleEntity);   //entity 가 DB로 수정

        }

        //DB -> 수정결과 페이지로 리 다이렉트
        return "redirect:/articles/" + articleEntity.getID();

        }
    @GetMapping("/articles/{ID}/delete")
    public String delete(@PathVariable Long ID, RedirectAttributes rdrms) {   //@Pathvariable을 지정하는이유 -> Long ID에 있는 ID를 article/[ID]에 있는
        log.info("삭제요청이 들어왔읍니다.");            //ID와 같은 ID를 가져오기위해서
        // 워크플로우 -
        //삭제대상을 가져온다(있는것만)
        Article articleEntity = articlearticlerepository.findById(ID).orElse(null);
        // db에 있는 삭제할 데이터를 찾아서 articleEntity(타겟=엔티티)를 가져온다
        log.info(articleEntity.toString());

        // 대상 삭제
        if(articleEntity != null){ //articleEntity 가 널이 아니라면
            articlearticlerepository.delete(articleEntity);    // DB에 있는 articleEntity(타겟)을 지운다(db에서 지움)
            rdrms.addFlashAttribute("message", "삭제가 완료되었읍니다");
        }

        // 삭제 완료 후 결과페이지로 리다이렉트!
        return "redirect:/articles";

    }


}
