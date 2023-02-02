package com.example.ia_application.app;

import java.util.ArrayList;
import java.util.Queue;
import java.sql.*;

public class toDoList {
    private Queue<toDoItem> toDoList;

    public toDoList() {
    }

    public toDoList(Queue<toDoItem> toDoList) {
        this.toDoList = toDoList;
    }

    public void addToDoItem(toDoItem item){
        toDoList.add(item);
    }

    public void removeToDoItem(toDoItem item){
        toDoList.remove(item);
    }

    public void markComplete(toDoItem item){
        item.setComplete(true);
    }

    public void markIncomplete(toDoItem item){
        item.setComplete(false);
    }

    public void markImportant(toDoItem item){
        item.setImportanceFlag(true);
    }

    public void markUnimportant(toDoItem item){
        item.setImportanceFlag(false);
    }

    public void sortToDoListDate(){

    }

    public void sortToDoListTime(){
        //TODO
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;


}
