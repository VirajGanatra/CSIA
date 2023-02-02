package com.example.ia_application.app;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class toDoItem {
    private String name;
    private String description;
    private  Duration expectedTime;
    private boolean importanceFlag;
    private boolean isComplete;
    private String category;
    private LocalDate dueDate;

    public toDoItem(String name, String description, Duration expectedTime, boolean importanceFlag, boolean isComplete, String category, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.expectedTime = expectedTime;
        this.importanceFlag = importanceFlag;
        this.isComplete = isComplete;
        this.category = category;
        this.dueDate = dueDate;
    }

    public toDoItem() {
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

    public Duration getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Duration expectedTime) {
        this.expectedTime = expectedTime;
    }

    public boolean isImportanceFlag() {
        return importanceFlag;
    }

    public void setImportanceFlag(boolean importanceFlag) {
        this.importanceFlag = importanceFlag;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime suggestTime(){
        return LocalDateTime.now();
    }

    public singleEvent Event(){
        return new singleEvent();
    }
}

