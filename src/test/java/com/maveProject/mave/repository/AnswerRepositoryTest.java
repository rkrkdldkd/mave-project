package com.maveProject.mave.repository;

import com.maveProject.mave.domain.*;
import com.maveProject.mave.repository.AnswerRepository;
import com.maveProject.mave.restController.AnswerApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 답변_모두(){
        //given


        Group group = new Group("group1");
        em.persist(group);

        Member member = new Member("user1");
        member.SetGroup(group);
        em.persist(member);

        QuestionBank questionBank = new QuestionBank();
        questionBank.setQuestionNumber(1l);
        questionBank.setContent("첫 번째 질문입니다.");

        em.persist(questionBank);

        Question question1 = new Question(group,questionBank.getContent(),questionBank.getQuestionNumber());
        em.persist(question1);

        Answer answer = new Answer("답변했다!");
        answer.addAnswer(question1);
        answer.registGroup(group);
        answer.registMember(member);
        em.persist(answer);

        List<AnswerApiController.AllAnswerResponse> result = answerRepository.findAllAnswerQuery(group.getId(), questionBank.getQuestionNumber());


        System.out.println("result = " + result.get(0).getAnswerContent());

        //when

        //then
    }

}