package com.example.ia_application.controllers;


import com.example.ia_application.driver;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

public class LoginController {
    @FXML
    private final Label welcomeText;
    public MFXButton registerButton;
    @FXML
    private MFXButton loginButton;

    @FXML
    private MFXTextField username;

    @FXML
    private MFXPasswordField password;


    public LoginController() {
        welcomeText = new Label("Welcome to the login screen!");
    }

    public LoginController(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        try {
            //System.out.print(username.getText());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ia_application/home-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            driver.sceneStack.pushScene(stage.getScene());
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        try {
            //System.out.print(username.getText());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ia_application/register-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            driver.sceneStack.pushScene(stage.getScene());
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}