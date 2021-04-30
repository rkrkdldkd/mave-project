package com.maveProject.mave.repository;

import com.maveProject.mave.domain.QuestionBank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Repository
public class QuestionBankRepository {

    private final EntityManager em;

    public QuestionBank findById(Long questionBankId){
        QuestionBank questionBank = em.find(QuestionBank.class, questionBankId);
        return questionBank;
    }

    public List<QuestionBank> findByNumber(Long questionNumber){
       return em.createQuery("select q from QuestionBank q where q.questionNumber = :number",QuestionBank.class)
               .setParameter("number",questionNumber)
               .getResultList();
    }
}
