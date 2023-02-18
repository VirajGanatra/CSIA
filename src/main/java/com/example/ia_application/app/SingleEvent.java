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
    public SingleEvent(){}
    public SingleEvent(String name, String description, int numBlocks, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name, description, date, startTime, endTime);
    }

    public SingleEvent(String name, String description, int numBlocks, LocalDate date, LocalTime startTime, Duration duration) {
        super(name, description, date, startTime, duration);
    }

    @Override
    public void addToDB(){

        //add to table called single which already exists, and has the same columns as the event table
        String sql = "INSERT INTO single (name, description, Date, 'Start Time', 'End Time') VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBClass.connection; PreparedStatement prst = connection.prepareStatement(sql);) {
            prst.setString(1, this.getName());
            prst.setString(2, this.getDescription());
            prst.setLong(3, this.getStartDate().toEpochDay());
            prst.setString(4, this.getStartTime().toString());
            prst.setString(5, this.getEndTime().toString());
            prst.executeUpdate();
            DBTablePrinter.printTable(connection, "single");
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

}
