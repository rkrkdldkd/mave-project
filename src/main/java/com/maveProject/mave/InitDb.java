package com.maveProject.mave;

import com.maveProject.mave.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 임시로 DB에 시작 데이터 넣음
 */

@Profile("local")
@RequiredArgsConstructor
@Component
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }


    @Transactional
    @RequiredArgsConstructor
    @Component
    static class InitService{

        private final EntityManager em;

        public void dbInit(){
            QuestionBank questionBank1 = new QuestionBank();
            questionBank1.setQuestionNumber(1l);
            questionBank1.setContent("첫 번째 질문입니다.");

            QuestionBank questionBank2 = new QuestionBank();
            questionBank2.setQuestionNumber(2l);
            questionBank2.setContent("두 번째 질문입니다.");

            QuestionBank questionBank3 = new QuestionBank();
            questionBank3.setQuestionNumber(3l);
            questionBank3.setContent("세 번째 질문입니다.");

            QuestionBank questionBank4 = new QuestionBank();
            questionBank4.setQuestionNumber(4l);
            questionBank4.setContent("네 번째 질문입니다.");

            QuestionBank questionBank5 = new QuestionBank();
            questionBank5.setQuestionNumber(5l);
            questionBank5.setContent("다섯 번째 질문입니다.");

            em.persist(questionBank1);
            em.persist(questionBank2);
            em.persist(questionBank3);
            em.persist(questionBank4);
            em.persist(questionBank5);

            Member member = new Member("hello1");
            em.persist(member);

            Group group = new Group("그루비룸", LocalDateTime.now(), IsFinish.NO, Flower.ZERO, 1l);
            em.persist(group);
        }

    }
}
