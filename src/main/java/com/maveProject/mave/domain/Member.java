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

    private String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="group_id")
    private Group group;






    //======= 생성 메서드 ========//

    public Member(String userName) {
        this.userName = userName;
    }


    //======= 연관관계 매서드 ========//

    public void SetGroup(Group group){
        this.group = group;
        group.getMembers().add(this);
    }

    //====== 비즈니스 로직 ======//

}
