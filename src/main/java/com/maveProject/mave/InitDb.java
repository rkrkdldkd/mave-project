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
            questionBank1.setContent("1. 자신의 좋은 추억 장소는? ");

            QuestionBank questionBank2 = new QuestionBank();
            questionBank2.setQuestionNumber(2l);
            questionBank2.setContent("2. 가장 행복했던 기억은? ");

            QuestionBank questionBank3 = new QuestionBank();
            questionBank3.setQuestionNumber(3l);
            questionBank3.setContent("3. 아들의 꿈은? ");

            QuestionBank questionBank4 = new QuestionBank();
            questionBank4.setQuestionNumber(4l);
            questionBank4.setContent("4. 어머니의 어릴 적 꿈은? ");

            QuestionBank questionBank5 = new QuestionBank();
            questionBank5.setQuestionNumber(5l);
            questionBank5.setContent("5. 아버지가 좋아하는 음식은? ");

            em.persist(questionBank1);
            em.persist(questionBank2);
            em.persist(questionBank3);
            em.persist(questionBank4);
            em.persist(questionBank5);

            Member member = new Member("hello","48690","hello");
            em.persist(member);

            Group group = new Group("그루비룸", LocalDateTime.now(), IsFinish.NO, Flower.ZERO, 1l,1l);

            member.SetGroup(group);
            em.persist(group);

            Question question = new Question(group,questionBank1.getContent(),questionBank1.getQuestionNumber());
            group.setCount();
            em.persist(question);

            Answer answer = new Answer("미리 한 답변입니다!");


            answer.registMember(member);
            answer.addAnswer(question);
            answer.registGroup(group);
            group.minusCount();
            group.compareState();
            em.persist(answer);

        }

    }
}
