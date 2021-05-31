package com.maveProject.mave;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class Testststst {

    @Test
    void teststst(){
        LocalTime localTime = LocalTime.of(23, 10);
        System.out.println("localTime = " + localTime);
        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);
        boolean before = localTime.isBefore(now);

        System.out.println("before = " + before);
    }
}
