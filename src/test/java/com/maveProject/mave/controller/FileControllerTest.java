package com.maveProject.mave.controller;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {

    @Test
    public void localDateTimeTest() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        //given
        Thread.sleep(100);
        LocalDateTime now1 = LocalDateTime.now();

        System.out.println("now = " + now);
        System.out.println("now1 = " + now1);


        //when

        //then
    }

}