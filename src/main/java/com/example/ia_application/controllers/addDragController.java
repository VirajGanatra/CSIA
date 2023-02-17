package com.example.ia_application.controllers;

import com.example.ia_application.app.event;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addDragController {
    public MFXTextField nameS;
    public MFXButton addButton;
    public MFXTextField descS;

    private final homeController homeController;
    private final Stage stage;

    public addDragController(homeController homeController) {
        System.out.println("add drag controller");
        this.homeController = homeController;
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/addDrag-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add drag controller2");
    }

    @FXML
    public void initialize() {
        addButton.setOnAction(this::returnHome);
    }


    public void returnHome(ActionEvent actionEvent) {
        System.out.println("returning home");
    }

    public void show() {
        stage.showAndWait();

    }
}
