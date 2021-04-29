package com.maveProject.mave.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Flower {

    @Id
    @GeneratedValue
    @Column(name="flower_id")
    Long id;
}
