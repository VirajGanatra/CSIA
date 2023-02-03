package com.example.ia_application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class excircle extends Application {

    @Override
    public void start(Stage primaryStage) {
        /*Circle blueCircle = new Circle(200, 200, 150);
        blueCircle.setFill(Color.BLUE);*/

        Arc sector = new Arc(200, 200, 150, 150, 0, 60);
//        sector.setFill(Color.WHITE);
        sector.setFill(Color.BLUE);
        sector.setType(ArcType.ROUND);

        Arc sector2 = new Arc(200, 200, 150, 150, 0, 60);
        sector2.setFill(Color.GREEN);
        sector2.setType(ArcType.ROUND);

        Pane root = new Pane();
//        root.getChildren().addAll(blueCircle, sector);
        root.getChildren().addAll(sector, sector2);

        Scene scene = new Scene(root, 400, 400);

        scene.setOnMouseMoved(e -> {
//            double angle = Math.atan2(e.getY() - blueCircle.getCenterY(), e.getX() - blueCircle.getCenterX()) * 180 / Math.PI;
            double angle = Math.atan2(e.getY() - sector.getCenterY(), e.getX() - sector.getCenterX()) * 180 / Math.PI;
            sector.setStartAngle(-angle - 30);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
