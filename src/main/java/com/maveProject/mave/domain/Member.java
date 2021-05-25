package com.maveProject.mave.domain;

import com.maveProject.mave.util.MD5Generator;
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
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String password;

    private String userName;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();


    //======= 생성자 ========//

    public Member(String userName) {
        this.userName = userName;
    }

    public Member(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }

    //====== Setter =======//
    public void setPassword(String password) {
        this.password = password;
    }


    //======= 연관관계 매서드 ========//

    public void SetGroup(Group group) {
        this.group = group;
        group.getMembers().add(this);
    }

    //====== 비즈니스 로직 ======//

    public void changePasswordToHash() {
        password = Integer.toString(password.hashCode());
    }


}
