package com.maveProject.mave.repository;

import com.maveProject.mave.domain.QQuestion;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.restController.AnswerApiController;
import com.maveProject.mave.restController.QuestionApiController;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.maveProject.mave.domain.QGroup.group;
import static com.maveProject.mave.domain.QQuestion.question;


@Repository
public class QuestionRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public QuestionRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

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

//    public List<Question> findByNumberForGroup(Long groupId, Long questionNumber){
//        return em.createQuery("select q from Question q join q.group g " +
//                "where q.questionNumber = :questionNumber and g.id = :groupId",Question.class)
//                .setParameter("questionNumber",questionNumber)
//                .setParameter("groupId",groupId)
//                .getResultList();
//    }

    public List<Question> findByNumberForGroupQuery(Long groupId, Long questionNumber){
        return queryFactory
                .selectFrom(question)
                .join(question.group,group).fetchJoin()
                .where( group.id.eq(groupId),
                        question.questionNumber.eq(questionNumber)
                )
                .fetch();
    }

    public List<QuestionApiController.GiveAllQuestionResponse> findAllQuestionForGroup(Long groupId, Long questionNumber) {
        return  queryFactory
                .select(Projections.constructor(QuestionApiController.GiveAllQuestionResponse.class,
                question.questionContent))
                .from(question)
                .join(question.group,group)
                .where(question.questionNumber.loe(questionNumber))
                .orderBy(question.questionNumber.desc())
                .fetch();

    }



}
