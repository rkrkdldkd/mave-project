package com.maveProject.mave.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer {

    @Id
    @GeneratedValue
    @Column(name="answer_id")
    private Long id;

    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name="group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="member_id")
    private Member member;



    //====== 생성 메서드=====//


    public Answer(String answerContent) {
        this.answerContent = answerContent;
    }



    //====== 연관관계 메서드 ======//
    public void addAnswer(Question question){
        this.question = question;
        question.getAnswers().add(this);
    }

    public void registGroup(Group group){
        this.group = group;
        group.getAnswers().add(this);
    }

    public void registMember(Member member){
        this.member = member;
        member.getAnswers().add(this);
    }




}
