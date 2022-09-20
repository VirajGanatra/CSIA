package com.example.ia_application.app;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class event {

    private String name;
    private String description;
    private int numBlocks;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Duration duration;
    public event(){}

    public event(String name, String description, int numBlocks, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.description = description;
        this.numBlocks = numBlocks;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
