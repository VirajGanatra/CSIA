package com.example.ia_application.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegisterController{
    @FXML
    private final Label welcomeText;

    public RegisterController() {
        welcomeText = new Label("Welcome to the login screen!");
    }

    public RegisterController(Label welcomeText) {
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
