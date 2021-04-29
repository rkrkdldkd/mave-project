package com.maveProject.mave.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Answer {

    @Id
    @GeneratedValue
    @Column(name="answer_id")
    Long id;

    String answer;

    @ManyToOne
    @JoinColumn(name="question_id")
    Question question;
}
