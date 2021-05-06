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
@Table(name="groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name="group_id")
    private Long id;

    private String groupName;

    private int remainCount;

    @Enumerated(EnumType.STRING)
    private IsFinish status;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL )
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL )
    private List<Answer> answers = new ArrayList<>();




    //===== 생성 메서드 =====//

    public Group(String groupName) {
        this.groupName = groupName;
    }




    //===== 연관관계 메서드 =====//

    public void setRemainCountCount(int count) {
        this.remainCount = count;
    }

    public void setStatus(IsFinish status) {
        this.status = status;
    }

    //====== 비즈니스 메서드 =====//




    /**
     * 질문 가져오기
     * 질문 테이블에 접근해서 그룹 넘버에 맞는 질문 싹 다 끌어오기
     * 기존에 있던 거라면 조인을 해서 싹 다 끌어오기기
     */




}
