package com.example.ia_application.controllers;

import com.example.ia_application.app.Event;
import com.example.ia_application.driver;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddDragController {
    public MFXTextField nameS;
    public MFXButton addButton;
    public MFXTextField descS;

    private final HomeController homeController;
    private final Stage stage;
    private final Event currentEvent;

    public AddDragController(HomeController homeController, Event currentEvent) {
        this.homeController = homeController;
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/addDrag-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            driver.sceneStack.pushScene(stage.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.currentEvent = currentEvent;
    }

    @FXML
    public void initialize() {
        addButton.setOnAction(this::addEvent);
    }

    public void addEvent(ActionEvent actionEvent) {
        currentEvent.setName(nameS.getText());
        currentEvent.setDescription(descS.getText());
        currentEvent.setEndTime(currentEvent.getStartTime());
        currentEvent.printEvent();

        //returnHome();
    }



    public void returnHome() {
        driver.sceneStack.popScene();
        stage.setScene(driver.sceneStack.getScene());

    }

    public void show() {
        stage.showAndWait();

    }
}
