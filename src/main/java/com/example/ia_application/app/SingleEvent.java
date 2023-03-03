package com.example.ia_application.app;

import com.example.ia_application.defaults.DBClass;
import com.example.ia_application.defaults.DBTablePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;


public class SingleEvent extends Event {
    private ToDoItem toDoItem;

    public SingleEvent(){}

    public SingleEvent(String name, String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name, description, date, startTime, endTime);
    }

    public SingleEvent(String name, String description, LocalDate date, LocalTime startTime, Duration duration) {
        super(name, description, date, startTime, duration);
    }

    public SingleEvent(String name, String description, LocalDate date, LocalTime startTime, LocalTime endTime, ToDoItem toDoItem) {
        super(name, description, date, startTime, endTime);
        this.toDoItem = toDoItem;
    }

    public SingleEvent(String name, String description, LocalDate date, LocalTime startTime, Duration duration, ToDoItem toDoItem) {
        super(name, description, date, startTime, duration);
        this.toDoItem = toDoItem;
    }

    public ToDoItem getToDoItem() {
        return toDoItem;
    }

    public void setToDoItem(ToDoItem toDoItem) {
        this.toDoItem = toDoItem;
    }

    @Override
    public void addToDB(){
        //add to table called single which already exists, and has the same columns as the event table
        String sql = "INSERT INTO single (name, description, Date, 'Start Time', 'End Time', 'To Do') VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBClass.connection;) {
            assert connection != null;
            try (PreparedStatement prst = connection.prepareStatement(sql);) {
                prst.setString(1, this.getName());
                prst.setString(2, this.getDescription());
                prst.setLong(3, this.getStartDate().toEpochDay());
                prst.setString(4, this.getStartTime().toString());
                prst.setString(5, this.getEndTime().toString());
                prst.setString(6, "toDoItem");
                prst.executeUpdate();
                DBTablePrinter.printTable(connection, "single");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
