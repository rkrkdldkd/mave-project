package com.maveProject.mave.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="Groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name="groups_id")
    Long id;

    String groupName;

    String question;

    String answer;

    int number;


}
