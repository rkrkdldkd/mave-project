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
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    private int remainCount;

    @Enumerated(EnumType.STRING)
    private IsFinish status;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();


    //===== 생성 메서드 =====//

    public Group(String groupName) {
        this.groupName = groupName;
    }


    //===== 연관관계 메서드 =====//

    //====== 비즈니스 메서드 =====//

    public void setRemainCountCount(int count) {
        this.remainCount = count;
    }

    public void setStatus(IsFinish status) {
        this.status = status;
    }


    public void minusCount() {
        if (this.getRemainCount() == 0) {
            return;
        }
        this.remainCount--;
    }

    public int setCount() {
        remainCount = this.getMembers().size();
        return remainCount;
    }

    public Boolean compareState() {
        if (remainCount == 0) {
            status = IsFinish.YES;
            return true;
        }
        return false;
    }


}
