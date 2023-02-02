package com.example.ia_application.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class singleEvent extends event{
    public singleEvent(){}
    public singleEvent(String name, String description, int numBlocks, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name, description, numBlocks, date, startTime, endTime);
    }

    public singleEvent(String name, String description, int numBlocks, LocalDate date, LocalTime startTime, Duration duration) {
        super(name, description, numBlocks, date, startTime, duration);
    }

}
