package com.example.ia_application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class loginController {
    @FXML
    private Label welcomeText;

    public loginController(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("testing");
    }
}