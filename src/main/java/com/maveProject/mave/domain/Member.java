package com.maveProject.mave.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    private String userId;

    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;


    //======= 생성 메서드 ========//

    public Member(String userId) {
        this.userId = userId;
    }


    //======= 연관관계 매서드 ========//

    public void SetGroup(Group group){
        this.group = group;
        group.getMembers().add(this);
    }

    //====== 비즈니스 로직 ======//
    public void registAnswer(Group group, String content){
        this.group = group;

    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", group=" + group +
                '}';
    }
}
