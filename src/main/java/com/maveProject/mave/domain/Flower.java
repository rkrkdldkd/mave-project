package com.maveProject.mave.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public enum Flower {
    ZERO(0),ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5);

    private int status;

    Flower(int status){
        this.status = status;
    }

    public int flowerStatus(){
        return status;
    }

}
