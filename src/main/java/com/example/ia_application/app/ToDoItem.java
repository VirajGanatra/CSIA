package com.example.ia_application.app;
import com.example.ia_application.defaults.DBClass;
import com.example.ia_application.defaults.DBTablePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ToDoItem {
    private String name;
    private String description;
    private  Duration expectedTime;
    private boolean importanceFlag;
    private boolean isComplete;
    private String category;
    private LocalDate dueDate;

    public ToDoItem(String name, String description, Duration expectedTime, boolean importanceFlag, boolean isComplete, String category, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.expectedTime = expectedTime;
        this.importanceFlag = importanceFlag;
        this.isComplete = isComplete;
        this.category = category;
        this.dueDate = dueDate;
    }

    public ToDoItem() {
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

    public boolean getImportanceFlag() {
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

    public SingleEvent toEvent(){
        return new SingleEvent(name, description, dueDate, suggestTime().toLocalTime(), expectedTime);
    }

    public void addToDB(){
        String sql = "INSERT INTO toDo (name, description, 'Expected Time', 'Importance', Complete, Category, 'Due Date') VALUES(?,?,?,?,?,?,?)";

        try (Connection connection = DBClass.connection;){
            assert connection != null;
            try (PreparedStatement prst = connection.prepareStatement(sql);){
                prst.setString(1, this.getName());
                prst.setString(2, this.getDescription());
                prst.setString(3, this.getExpectedTime().toString());
                prst.setInt(4, this.getImportanceFlag()? 1 : 0);
                prst.setInt(5, this.isComplete()? 1 : 0);
                prst.setString(6, this.getCategory());
                prst.setLong(7, this.getDueDate().toEpochDay());
                prst.executeUpdate();
                DBTablePrinter.printTable(connection, "toDo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

