package com.example.ia_application.controllers;


import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


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

    @FXML
    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;
    @FXML
    private BorderPane borderPane;

    private Stage stage;

    public LoginController() {

    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) password.getScene().getWindow();
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\viraj\\AppData\\Local\\Programs\\Python\\Python39\\python.exe", "C:\\Users\\viraj\\OneDrive - Jumeirah English Speaking School\\School\\IB\\CS\\IA_Application\\src\\main\\resources\\com\\example\\ia_application\\loginDB.py", username.getText(), password.getText());
        BufferedReader in = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
        String ret = in.readLine();

//        while ((ret = in.readLine()) != null) {
//            System.out.println(ret);
//        }
        if (ret!=null && ret.equals("True")) {
            System.out.println("Login Successful");
            HomeController homeController = new HomeController(this);
            homeController.show();
        } else {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    .setContentText("Login failed!")
                    .setHeaderIcon(new MFXFontIcon("mfx-exclamation-circle-filled", 24))
                    .setHeaderText("Login: Error")
                    .makeScrollable(true)
                    .get();
            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner((Stage)((Node) event.getSource()).getScene().getWindow())
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle("Dialogs Preview")
                    .setOwnerNode(borderPane)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.addActions(
                    Map.entry(new MFXButton("Retry"), e -> dialog.close())
            );

            dialogContent.setMaxSize(300, 100);
            convertDialogTo("mfx-error-dialog");
            dialog.show();
        }

        /*HomeController homeController = new HomeController(this);
        homeController.show();*/
    }

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        RegisterController registerController = new RegisterController(this);
        registerController.show();
    }

    public Stage getStage() {
        return stage;
    }


}