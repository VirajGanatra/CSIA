package com.example.ia_application.app;

import java.util.LinkedList;
import java.sql.*;

public class ToDoList {
    private LinkedList<ToDoItem> toDoList;

    public ToDoList() {
    }

    public ToDoList(LinkedList<ToDoItem> toDoList) {
        this.toDoList = toDoList;
    }

    public void addToDoItem(ToDoItem item){
        toDoList.add(item);
    }

    public void removeToDoItem(ToDoItem item){
        toDoList.remove(item);
    }

    public void markComplete(ToDoItem item){
        item.setComplete(true);
    }

    public void markIncomplete(ToDoItem item){
        item.setComplete(false);
    }

    public void markImportant(ToDoItem item){
        item.setImportanceFlag(true);
    }

    public void markUnimportant(ToDoItem item){
        item.setImportanceFlag(false);
    }

    public void sortToDoListDate(){
    //sort via bubblesort
        for (int i = 0; i < toDoList.size(); i++) {
            for (int j = 0; j < toDoList.size() - i - 1; j++) {
                if (toDoList.get(j).getDueDate().compareTo(toDoList.get(j + 1).getDueDate()) > 0 ) {
                    ToDoItem temp = toDoList.get(j);
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
                    ToDoItem temp = toDoList.get(j);
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
