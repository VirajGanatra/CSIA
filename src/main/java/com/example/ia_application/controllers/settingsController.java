package com.example.ia_application.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;


public class settingsController {

    public TabPane tabPane;
    public Tab t1;
    public Tab t2;
    int swidth;


    public void start(Stage primaryStage) {
        //Get primary screen bounds
        /*Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        swidth = (int) screenBounds.getWidth();

        tabPane.setTabMinWidth(swidth/2);
        tabPane.setTabMaxWidth(swidth/2);*/

        //tabPane.tabMinWidthProperty().bind(tabPane.getScene().widthProperty().divide(tabPane.getTabs().size()));









    }

}
