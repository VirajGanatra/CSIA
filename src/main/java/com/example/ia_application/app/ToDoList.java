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

//    public void sortToDoListDate(){
//    //sort via bubblesort
//        for (int i = 0; i < toDoList.size(); i++) {
//            for (int j = 0; j < toDoList.size() - i - 1; j++) {
//                if (toDoList.get(j).getDueDate().compareTo(toDoList.get(j + 1).getDueDate()) > 0 ) {
//                    ToDoItem temp = toDoList.get(j);
//                    toDoList.set(j, toDoList.get(j + 1));
//                    toDoList.set(j + 1, temp);
//                }
//            }
//        }
//    }

    public void sortToDoListDate(){
        //sort via insertion sort
        for (int i = 1; i < toDoList.size(); i++) {
            ToDoItem key = toDoList.get(i);
            int j = i - 1;
            while (j >= 0 && toDoList.get(j).getDueDate().compareTo(key.getDueDate()) > 0) {
                toDoList.set(j + 1, toDoList.get(j));
                j = j - 1;
            }
            toDoList.set(j + 1, key);
        }
    }

    public void sortToDoListImportance(){
        //bubble sort with importance flag - if both are important, sort by date
        for (int i = 0; i < toDoList.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < toDoList.size() - i - 1; j++) {
                if (toDoList.get(j).getImportanceFlag() && !toDoList.get(j + 1).getImportanceFlag() || ( toDoList.get(j).getDueDate().compareTo(toDoList.get(j + 1).getDueDate()) > 0 && toDoList.get(j).getImportanceFlag() == toDoList.get(j + 1).getImportanceFlag())) {
                    ToDoItem temp = toDoList.get(j);
                    toDoList.set(j, toDoList.get(j + 1));
                    toDoList.set(j + 1, temp);
                    flag = true;
                }
            }
            if (!flag) { break; }
        }
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;


}
