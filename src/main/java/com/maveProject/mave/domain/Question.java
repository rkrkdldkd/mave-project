package com.maveProject.mave.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Question {

    @Id
    @GeneratedValue
    @Column(name="question_id")
    private Long id;

    private String questionContent;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    //===== 연관관계 메서드 =====//

    public void addQuestion(Group group){
        this.group = group;
        group.getQuestions().add(this);
    }





}
