package com.maveProject.mave.repository;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.domain.QuestionBank;
import com.maveProject.mave.restController.QuestionApiController;
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

    @Test
    public void 질문_가져오기(){
        //given
        Group group = new Group("group1");
        em.persist(group);

        QuestionBank questionBank1 = new QuestionBank();
        questionBank1.setQuestionNumber(1l);
        questionBank1.setContent("첫 번째 질문입니다.");

        QuestionBank questionBank2 = new QuestionBank();
        questionBank2.setQuestionNumber(2l);
        questionBank2.setContent("두 번째 질문입니다.");

        em.persist(questionBank1);

        Question question1 = new Question(group,questionBank1.getContent(),questionBank1.getQuestionNumber());
        Question question2 = new Question(group,questionBank2.getContent(),questionBank2.getQuestionNumber());
        em.persist(question1);
        em.persist(question2);

        List<QuestionApiController.GiveAllQuestionResponse> questions = questionRepository.findAllQuestionForGroup(group.getId(), 2l);

        for (QuestionApiController.GiveAllQuestionResponse question : questions) {
            System.out.println("question = " + question.getQuestionContent());
        }
        //when

        //then
    }



}