package com.example.ia_application.controllers;


import com.example.ia_application.Driver;
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
import java.time.LocalDate;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

public class LoginController {
    @FXML
    public MFXButton registerButton;
    @FXML
    private MFXButton loginButton;

    @FXML
    private MFXTextField username;

    @FXML
    private MFXPasswordField password;

    public LoginController() {
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        HomeController homeController = new HomeController(this);
        homeController.show();

    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {

    }


}