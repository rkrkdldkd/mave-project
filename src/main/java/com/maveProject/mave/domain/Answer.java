package com.maveProject.mave.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Answer {

    @Id
    @GeneratedValue
    @Column(name="answer_id")
    private Long id;


    private String answerContent;


    @ManyToOne
    @JoinColumn(name="qestion_id")
    private Question question;




    //====== 연관관계 메서드 ======//
    public void addAnswer(Question question){
        this.question = question;
        question.getAnswers().add(this);
    }



}
