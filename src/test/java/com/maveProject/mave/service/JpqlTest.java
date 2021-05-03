package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.repository.GroupRepository;
import com.maveProject.mave.repository.MemberRepository;
import com.maveProject.mave.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
public class JpqlTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    EntityManager em;

    @Transactional
    @Test
    public void jqpl_test(){
        Group group = new Group("hello111");
        em.persist(group);

        Long questionId = questionService.createQuestion(group.getId(), "하하하하", 1l);
        Question question = em.find(Question.class, questionId);

        System.out.println("groupId = " + group.getId());
        System.out.println("question.getGroup().getId() = " + question.getGroup().getId());



        Question question1 = questionRepository.findByNumberForGroup(group.getId(), question.getQuestionNumber()).get(0);

        System.out.println("question1.getQuestionContent() = " + question1.getQuestionContent());


        //when

        //then
    }
}
