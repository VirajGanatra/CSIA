package com.example.ia_application.app;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class event {

    private String name;
    private String description;
    private int numBlocks;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private Duration duration;
    public event(){}

    public event(String name, String description, int numBlocks, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.description = description;
        this.numBlocks = numBlocks;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }

    public event(String name, String description, int numBlocks, LocalDate startDate, LocalTime startTime, Duration duration) {
        this.name = name;
        this.description = description;
        this.numBlocks = numBlocks;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = startTime.plus(duration);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumBlocks() {
        return numBlocks;
    }

    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void addToCalendar(){}

}
