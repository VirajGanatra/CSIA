package com.example.ia_application.app;

import javafx.scene.Scene;

import java.util.LinkedList;

public class SceneStack {
    private final LinkedList<Scene> sceneStack;

    public SceneStack(){
        sceneStack = new LinkedList<>();
    }
    public void pushScene(Scene scene){
        sceneStack.add(scene);
    }
    public void popScene(){
        sceneStack.removeLast();
    }
    public Scene getScene(){
        return sceneStack.getLast();
    }
    public boolean isEmpty(){
        return sceneStack.isEmpty();
    }
    public void clear(){
        sceneStack.clear();
    }
    public int size(){
        return sceneStack.size();
    }


}
