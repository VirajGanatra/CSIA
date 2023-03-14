package com.example.ia_application.app;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.*;

public class ToDoList {

    static class Node {
        ToDoItem item;
        LinkedListToDo.Node below;
        Node(ToDoItem item){
            this.item = item;
            below = null;
        }
    }

    private LinkedListToDo.Node top;
    private LinkedListToDo.Node bottom;
    private int size;

    public boolean isEmpty(){
        return size == 0;
    }

    public ToDoItem peekItem(){
        return top.item;
    }

    public void pushItem(ToDoItem item){
        LinkedListToDo.Node node = new LinkedListToDo.Node(item);
        if(isEmpty()){
            top = node;
            bottom = node;
        } else {
            node.below = top;
            top = node;
        }
        size++;
    }


    public void clear(){
        top = null;
        bottom = null;
        size = 0;
    } //Garbage collector removes the now unreachable nodes

    public ToDoItem getItem(int index){
        if(index < 0 || index >= size){
            return null;
        } else {
            LinkedListToDo.Node node = top;
            for(int i = 0; i < index; i++){
                node = node.below;
            }
            return node.item;
        }
    }

    public void removeItem(int index){
        if (isEmpty()){
            return;
        } else if(index < 0 || index >= size){
            return;
        } else if(index == 0){
            top = top.below;
            size--;
        } else {
            LinkedListToDo.Node node = top;
            for(int i = 0; i < index - 1; i++){
                node = node.below;
            }
            node.below = node.below.below;
            size--;
        }
    }

    public ToDoItem getNextItem(LinkedListToDo.Node node){
        if(node.below == null){
            return null;
        } else {
            return node.below.item;
        }
    }

    private LinkedListToDo toDoList = new LinkedListToDo();
    public ToDoList() {
    }

    public ToDoList(LinkedListToDo toDoList) {
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

    public void addToDoItem(String name, String description, Duration expectedTime, boolean importanceFlag, boolean isComplete, String category, LocalDate dueDate){
        toDoList.add(new ToDoItem(name, description, expectedTime, importanceFlag, isComplete, category, dueDate));
    }

    public void removeToDoItem(String name){
        for (int i = 0; i<toDoList.getSize(); i++) {
            ToDoItem item = toDoList.get(i);
            if (item.getName().equals(name)) {
                toDoList.remove(item);
                break;
            }
        }
    }

    public void markComplete(String name){
        for (int i = 0; i<toDoList.getSize(); i++) {
            ToDoItem item = toDoList.get(i);
            if (item.getName().equals(name)) {
                item.setComplete(true);
                break;
            }
        }
    }

    public LinkedListToDo getToDoList() {
        return toDoList;
    }




    public void sortToDoListDate(){
        //sort via insertion sort
        for (int i = 1; i < toDoList.getSize(); i++) {
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
        for (int i = 0; i < toDoList.getSize(); i++) {
            boolean flag = false;
            for (int j = 0; j < toDoList.getSize() - i - 1; j++) {
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



}
