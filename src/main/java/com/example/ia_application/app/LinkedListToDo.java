package com.example.ia_application.app;

public class LinkedListToDo{


    static class Node {
        ToDoItem item;
        Node below;
        Node(ToDoItem item){
            this.item = item;
            below = null;
        }
    }

    private Node top;
    private Node bottom;
    private int size;

    public boolean isEmpty(){
        return size == 0;
    }

    public ToDoItem peekItem(){
        return top.item;
    }

    public void add(ToDoItem item){
        Node node = new Node(item);
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

    public ToDoItem get(int index){
        if(index < 0 || index >= size){
            return null;
        } else {
            Node node = top;
            for(int i = 0; i < index; i++){
                node = node.below;
            }
            return node.item;
        }
    }

    public void remove(int index){
        if (isEmpty()){
            return;
        } else if(index < 0 || index >= size){
            return;
        } else if(index == 0){
            top = top.below;
            size--;
        } else {
            Node node = top;
            for(int i = 0; i < index - 1; i++){
                node = node.below;
            }
            node.below = node.below.below;
            size--;
        }
    }

    public void remove(ToDoItem item){
        if (isEmpty()){
            return;
        } else if(item == null){
            return;
        } else {
            Node node = top;
            for(int i = 0; i < size; i++){
                if(node.item.equals(item)){
                    remove(i);
                    return;
                }
                node = node.below;
            }
        }
    }

    public ToDoItem getNext(Node node){
        if(node.below == null){
            return null;
        } else {
            return node.below.item;
        }
    }

    public int getSize(){
        return size;
    }

    public void set(int index, ToDoItem item){
        if(index < 0 || index >= size){
            return;
        } else {
            Node node = top;
            for(int i = 0; i < index; i++){
                node = node.below;
            }
            node.item = item;
        }
    }
}
