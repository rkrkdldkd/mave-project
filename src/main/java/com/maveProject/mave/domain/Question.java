package com.maveProject.mave.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Question {

    @Id
    @GeneratedValue
    @Column(name="question_id")
    Long id;


    int number;

    String question;




}
