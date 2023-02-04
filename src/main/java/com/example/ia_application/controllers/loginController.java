package com.example.ia_application.controllers;


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

public class loginController{
    @FXML
    private final Label welcomeText;
    public MFXButton registerButton;
    @FXML
    private MFXButton loginButton;

    @FXML
    private MFXTextField username;

    @FXML
    private MFXPasswordField password;


    public loginController() {
        welcomeText = new Label("Welcome to the login screen!");
    }

    public loginController(Label welcomeText) {
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
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}