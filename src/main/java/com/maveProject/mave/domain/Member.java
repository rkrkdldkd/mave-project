package com.maveProject.mave.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    Long id;

    String name;

    String userId;

    String phoneNumber;

    String answer;

    @ManyToOne
    @JoinColumn(name="group_id")
    Group group;


    //======= 생성자 ========//

    public Member(){};

    public Member(String userId) {
        this.userId = userId;
    }
}
