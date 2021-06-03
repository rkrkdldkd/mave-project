package com.maveProject.mave.repository;

import com.maveProject.mave.domain.CustomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepository extends JpaRepository<CustomQuestion,Long> {

    @Query("SELECT C.questionContent FROM CustomQuestion C WHERE C.groupId = ?1 and C.diaryDate = ?2")
    String findCustomQuestion(Long groupId,Long diaryDate);

}
