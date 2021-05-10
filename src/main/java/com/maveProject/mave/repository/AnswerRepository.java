package com.maveProject.mave.repository;


import com.maveProject.mave.domain.Answer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Repository
public class AnswerRepository {

    private final EntityManager em;

    public Long save(Answer answer) {
        em.persist(answer);
        return answer.getId();
    }

    public List<Answer> findAllAnswer(Long groupId, Long questionNumber){
        return em.createQuery("select a from Answer a " +
                " join fetch a.group g" +
                " join fetch a.member m" +
                " join fetch a.question q" +
                " where g.id = :groupId" +
                " and q.questionNumber = :questionNumber", Answer.class)
                .setParameter("groupId", groupId)
                .setParameter("questionNumber", questionNumber)
                .getResultList();
    }


}
