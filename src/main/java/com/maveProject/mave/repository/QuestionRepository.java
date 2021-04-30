package com.maveProject.mave.repository;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class QuestionRepository {

    private final EntityManager em;

    public Long save(Question question){
        em.persist(question);
        return question.getId();
    }

    public Question findById(Long questionId){
        Question question = em.find(Question.class, questionId);
        return question;
    }

    public List<Question> findByNumber(Long questionNumber){
        return em.createQuery("select q from Question q where q.questionNumber = :questionNumber",
                Question.class)
                .setParameter("questionNumber", questionNumber)
                .getResultList();
    }
}
