package com.maveProject.mave.repository;


import com.maveProject.mave.domain.Answer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.maveProject.mave.domain.QAnswer.answer;
import static com.maveProject.mave.domain.QGroup.group;
import static com.maveProject.mave.domain.QMember.member;
import static com.maveProject.mave.domain.QQuestion.question;

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

    public List<Answer> findAllAnswerQuery(Long groupId, Long questionNumber){
        List<Answer> result = queryFactory.select(answer)
                .from(answer)
                .join(answer.group, group).fetchJoin()
                .join(answer.member, member).fetchJoin()
                .join(answer.question, question).fetchJoin()
                .where(
                        group.id.eq(groupId),
                        question.questionNumber.eq(questionNumber)
                )
                .fetch();
        return result;
    }


}
