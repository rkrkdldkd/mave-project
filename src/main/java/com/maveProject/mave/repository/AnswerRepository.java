package com.maveProject.mave.repository;


import com.maveProject.mave.domain.Answer;
import com.maveProject.mave.domain.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Getter
@RequiredArgsConstructor
@Repository
public class AnswerRepository {

    private final EntityManager em;

    public Long save(Answer answer){
        em.persist(answer);
        return answer.getId();
    }



}
