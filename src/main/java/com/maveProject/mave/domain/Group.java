package com.maveProject.mave.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="Groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name="groups_id")
    Long id;

    String groupName;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    String question;

    String answer;

    int number;




}
