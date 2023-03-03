package com.example.ia_application.controllers;


import com.example.ia_application.app.SingleEvent;
import com.example.ia_application.Driver;
import com.example.ia_application.defaults.Template;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddController {

    private final Stage stage;
    public MFXTextField desc;
    public MFXTextField name;
    public MFXComboBox<Integer> startH;
    public MFXComboBox<Integer> startM;
    public MFXComboBox<Integer> endH;
    public MFXComboBox<Integer> endM;
    public MFXCheckbox rec;
    public MFXButton add;
    final HomeController homeController;


    public AddController(HomeController homeController){
        this.homeController = homeController;
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/add-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            Driver.sceneStack.pushScene(stage.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        ObservableList<Integer> hours = Template.hours;
        ObservableList<Integer> minutes = Template.minutes;
        startH.setItems(hours);
        startM.setItems(minutes);
        endH.setItems(hours);
        endM.setItems(minutes);
        add.setOnAction(this::addClick); //this is the same as add.setOnAction(e -> addClick(e));
        rec.setOnAction(this::recClick); //these are method references; sometimes easier to see what is happening

    }

    public void show() {stage.showAndWait();}

    public void addClick(ActionEvent actionEvent) {
        SingleEvent currentEvent = new SingleEvent();
        currentEvent.setName(name.getText());
        currentEvent.setDescription(desc.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        currentEvent.setStartTime(LocalTime.parse(startH.getValue().toString() + ":" + startM.getValue().toString(), formatter));
        currentEvent.setEndTime(LocalTime.parse(endH.getValue().toString() + ":" + endM.getValue().toString(), formatter));
        currentEvent.setDuration();
        currentEvent.setStartDate(homeController.datePicker.getValue());
        currentEvent.addToDB();

        Driver.sceneStack.popScene();
        stage.setScene(Driver.sceneStack.peekScene());
    }

    public void recClick(ActionEvent actionEvent) {
        try{
            AddRecController addRecController = new AddRecController(this);
            addRecController.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
