package com.example.ia_application.app;

import javafx.scene.Scene;

public class SceneStack {
    static class Node {
        Scene scene;
        Node below;
        Node(Scene scene){
            this.scene = scene;
            below = null;
        }
    }

    private Node top;
    private Node bottom;
    private int size;

    public boolean isEmpty(){
        return size == 0;
    }

    public Scene peekScene(){
        return top.scene;
    }

    public void pushScene(Scene scene){
        Node node = new Node(scene);
        if(isEmpty()){
            top = node;
            bottom = node;
        } else {
            node.below = top;
            top = node;
        }
        size++;
    }
    
    public Scene popScene(){
        if(isEmpty()){
           return null;
        } else {
            Scene scene = top.scene;
            top = top.below;
            size--;
            return scene;
        }
    }
    
    public void clear(){
        top = null;
        bottom = null;
        size = 0;
    } //Garbage collector removes the now unreachable nodes


}
