package com.example.ia_application.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.sql.*;

public class toDoList {
    private LinkedList<toDoItem> toDoList;

    public toDoList() {
    }

    public toDoList(LinkedList<toDoItem> toDoList) {
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
    //sort via bubblesort
        for (int i = 0; i < toDoList.size(); i++) {
            for (int j = 0; j < toDoList.size() - i - 1; j++) {
                if (toDoList.get(j).getDueDate().compareTo(toDoList.get(j + 1).getDueDate()) > 0 ) {
                    toDoItem temp = toDoList.get(j);
                    toDoList.set(j, toDoList.get(j + 1));
                    toDoList.set(j + 1, temp);
                }
            }
        }

    }



    public void sortToDoListImportance(){
        //sort via bubblesort, but with a double
        for (int i = 0; i < toDoList.size(); i++) {
            for (int j = 0; j < toDoList.size() - i - 1; j++) {
                if (toDoList.get(j).getImportanceFlag() && !toDoList.get(j + 1).getImportanceFlag() || ( toDoList.get(j).getDueDate().compareTo(toDoList.get(j + 1).getDueDate()) > 0 && toDoList.get(j).getImportanceFlag() == toDoList.get(j + 1).getImportanceFlag() )) {
                    toDoItem temp = toDoList.get(j);
                    toDoList.set(j, toDoList.get(j + 1));
                    toDoList.set(j + 1, temp);
                }
            }
        }


    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;


}
