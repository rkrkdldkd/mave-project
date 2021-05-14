package com.maveProject.mave.repository;


import com.maveProject.mave.domain.Answer;
import com.maveProject.mave.restController.AnswerApiController;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.maveProject.mave.domain.QAnswer.answer;
import static com.maveProject.mave.domain.QGroup.group;
import static com.maveProject.mave.domain.QMember.member;
import static com.maveProject.mave.domain.QQuestion.question;
import static com.maveProject.mave.restController.AnswerApiController.*;

@Getter
@Repository
public class AnswerRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AnswerRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Long save(Answer answer) {
        em.persist(answer);
        return answer.getId();
    }

//    public List<Answer> findAllAnswer(Long groupId, Long questionNumber){
//        return em.createQuery("select a from Answer a " +
//                " join fetch a.group g" +
//                " join fetch a.member m" +
//                " join fetch a.question q" +
//                " where g.id = :groupId" +
//                " and q.questionNumber = :questionNumber", Answer.class)
//                .setParameter("groupId", groupId)
//                .setParameter("questionNumber", questionNumber)
//                .getResultList();
//    }

    public List<AllAnswerResponse> findAllAnswerQuery(Long groupId, Long questionNumber){
       return queryFactory.select(Projections.constructor(AllAnswerResponse.class,
               answer.member.id,
               answer.answerContent))
               .from(answer)
               .join(answer.group,group)
               .join(answer.member,member)
               .join(answer.question,question)
               .where(
                       group.id.eq(groupId),
                       question.questionNumber.eq(questionNumber)
               )
               .fetch();

    }


}
