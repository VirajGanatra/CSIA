package com.example.ia_application.app;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class recurringEvent extends event{

    private int[] daysOfWeek;
    private int[] weeksOfMonth;
    private LocalDate endDate;

    public recurringEvent(){}

    public recurringEvent(String name, String description, int numBlocks, LocalDate startDate, LocalTime startTime, LocalTime endTime, int[] daysOfWeek, int[] weeksOfMonth, LocalDate endDate) {
        super(name, description, startDate, startTime, endTime);
        this.daysOfWeek = daysOfWeek;
        this.weeksOfMonth = weeksOfMonth;
        this.endDate = endDate;
    }

    public recurringEvent(String name, String description, int numBlocks, LocalDate startDate, LocalTime startTime, Duration duration, int[] daysOfWeek, int[] weeksOfMonth, LocalDate endDate) {
        super(name, description, startDate, startTime, duration);
        this.daysOfWeek = daysOfWeek;
        this.weeksOfMonth = weeksOfMonth;
        this.endDate = endDate;
    }

    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int[] getWeeksOfMonth() {
        return weeksOfMonth;
    }

    public void setWeeksOfMonth(int[] weeksOfMonth) {
        this.weeksOfMonth = weeksOfMonth;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
