package com.maveProject.mave.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@RequiredArgsConstructor
@Entity
public class CustomQuestion {

    @Id @GeneratedValue
    @Column(name="customQuestion")
    private Long id;

    private String questionContent;

    private Long diaryDate;

    private Long groupId;

    public CustomQuestion(String questionContent, Long diaryDate, Long groupId) {
        this.questionContent = questionContent;
        this.diaryDate = diaryDate;
        this.groupId = groupId;
    }
}
