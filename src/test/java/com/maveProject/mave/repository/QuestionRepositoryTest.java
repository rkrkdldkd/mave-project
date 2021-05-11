package com.maveProject.mave.repository;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.domain.QuestionBank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestionRepositoryTest {
    
    @Autowired
    EntityManager em;
    
    @Autowired
    QuestionRepository questionRepository;
    
    @Test
    public void 그룹_질문_찾기(){
        Group group = new Group("group1");
        em.persist(group);

        QuestionBank questionBank = new QuestionBank();
        questionBank.setQuestionNumber(1l);
        questionBank.setContent("첫 번째 질문입니다.");

        em.persist(questionBank);

        Question question1 = new Question(group,questionBank.getContent(),questionBank.getQuestionNumber());
        em.persist(question1);

        //given
        List<Question> questions = questionRepository.findByNumberForGroupQuery(group.getId(), 1l);
        Question question = questions.get(0);

        System.out.println("question.getQuestionContent() = " + question.getQuestionContent());

        //when
        
        //then
    }



}