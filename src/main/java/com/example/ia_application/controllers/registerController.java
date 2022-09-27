package com.example.ia_application.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class registerController {
    @FXML
    private final Label welcomeText;

    public registerController() {
        welcomeText = new Label("Welcome to the login screen!");
    }

    public registerController(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    @FXML
    protected void onRegisterButtonClick() {
        welcomeText.setText("testing");
    }

    @FXML
    protected void onCreateButtonClick() {
        welcomeText.setText("testing");
    }
}
