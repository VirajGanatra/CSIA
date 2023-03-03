package com.example.ia_application.app;

import com.example.ia_application.defaults.DBClass;
import com.example.ia_application.defaults.DBTablePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RecurringEvent extends Event {

    private String daysOfWeek;
    private LocalDate endDate;
    private int[] daysOfWeekInt;

    public RecurringEvent(){}

    public RecurringEvent(String name, String description, int numBlocks, LocalDate startDate, LocalTime startTime, LocalTime endTime, String daysOfWeek, LocalDate endDate) {
        super(name, description, startDate, startTime, endTime);
        this.daysOfWeek = daysOfWeek;
        this.endDate = endDate;
    }

    public RecurringEvent(String name, String description, int numBlocks, LocalDate startDate, LocalTime startTime, Duration duration, String daysOfWeek, LocalDate endDate) {
        super(name, description, startDate, startTime, duration);
        this.daysOfWeek = daysOfWeek;
        this.endDate = endDate;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int[] getDaysOfWeekInt() {
        return daysOfWeekInt;
    }

    public void setDaysOfWeekInt(int[] daysOfWeekInt) {
        this.daysOfWeekInt = daysOfWeekInt;
    }

    public void setDaysOfWeekInt(String daysOfWeek) {
        //converts the string of days of the week to an array of ints
        String[] daysOfWeekArray = daysOfWeek.split(",");
        int[] daysOfWeekInt = new int[daysOfWeekArray.length];
        for (int i = 0; i < daysOfWeekArray.length; i++) {
            daysOfWeekInt[i] = Integer.parseInt(daysOfWeekArray[i]);
        }
        this.daysOfWeekInt = daysOfWeekInt;
    }

    @Override
    public void addToDB(){
        //add to table called single which already exists, and has the same columns as the event table
        String sql = "INSERT INTO recurring (name, description, 'Start Date', 'End Date', 'Start Time', 'End Time', 'Days On') VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBClass.connection; PreparedStatement prst = connection.prepareStatement(sql);) {
            prst.setString(1, this.getName());
            prst.setString(2, this.getDescription());
            prst.setLong(3, this.getStartDate().toEpochDay());
            prst.setLong(4, this.getEndDate().toEpochDay());
            prst.setString(5, this.getStartTime().toString());
            prst.setString(6, this.getEndTime().toString());
            prst.setString(7, this.getDaysOfWeek());
            prst.executeUpdate();
            DBTablePrinter.printTable(connection, "recurring");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
