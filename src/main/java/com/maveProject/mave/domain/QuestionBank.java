package com.maveProject.mave.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 임시로 만든 질문 은행
 */

@Getter
@Setter
@Entity
public class QuestionBank {

    @Id
    @GeneratedValue
    @Column(name="questionBank_id")
    private Long id;

    private Long questionNumber;

    private String content;
}
