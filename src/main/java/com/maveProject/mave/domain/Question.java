package com.maveProject.mave.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question {

    @Id
    @GeneratedValue
    @Column(name="question_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    private String questionContent;
    private Long questionNumber;

    //===== 생성 메서드 =====//

    public Question(Group group,String questionContent, Long questionNumber) {
        this.group = group;
        this.questionContent = questionContent;
        this.questionNumber = questionNumber;
    }


    //===== 연관관계 메서드 =====//

    public void addQuestion(Group group){
        this.group = group;
        group.getQuestions().add(this);
    }





}
