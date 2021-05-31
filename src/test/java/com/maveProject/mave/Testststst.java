package com.maveProject.mave;

import com.maveProject.mave.domain.Flower;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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


    @Test
    void testststDSFDSF(){
        LocalDate startDate = LocalDate.of(
               2021,
                5,
                31);

        LocalDate endDate = LocalDate.of(
                2021,
                6,
                1);


        long between = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("between = " + between);
    }




}
