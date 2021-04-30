package com.maveProject.mave.service;

import com.maveProject.mave.domain.Answer;
import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.domain.QuestionBank;
import com.maveProject.mave.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerServiceTest {

   
    @Autowired
    GroupService groupService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionBankService questionBankService;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    public void 답변_등록하기() {
        //given
        
        QuestionBank bank = questionBankService.findQuestion(1l);

        Group 그루비룸 = groupService.findGroup("그루비룸");
        Long question = questionService.createQuestion(그루비룸.getGroupName(), bank.getContent(), bank.getQuestionNumber());

        Question question1 = em.find(Question.class, question);

        Answer answer = new Answer(question1.getQuestionContent());
        answer.addAnswer(question1);
        em.persist(answer);
        
        em.flush();
        Answer answer1 = em.find(Answer.class, answer.getId());
        System.out.println("answer1.getAnswerContent() = " + answer1.getAnswerContent());


        //when

        //then
    }
}