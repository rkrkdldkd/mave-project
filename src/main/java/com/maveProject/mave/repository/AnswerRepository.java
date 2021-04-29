package com.maveProject.mave.repository;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Getter
@RequiredArgsConstructor
@Repository
public class AnswerRepository {

    private final EntityManager em;


}
