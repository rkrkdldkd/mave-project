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
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;


    private String userId;

    private String phoneNumber;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();




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

}
