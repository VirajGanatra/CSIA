module com.example.ia_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ia_application to javafx.fxml;
    exports com.example.ia_application.controllers;
    opens com.example.ia_application.controllers to javafx.fxml;
    exports com.example.ia_application;
}