package com.example.ia_application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Test1 extends Application {
    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(100);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        Arc sector = new Arc();
        sector.setRadiusX(80);
        sector.setRadiusY(80);
        sector.setStartAngle(45);
        sector.setLength(90);
        sector.setType(ArcType.ROUND);
        sector.setFill(Color.BLUE);

        Pane freePane = new Pane();
        freePane.getChildren().add(sector);

        StackPane stackPane = new StackPane();
        Pane fixedPane = new Pane();
        Arc fixedArc = new Arc(100, 100, 100, 100, 45, 90);
        fixedArc.setType(ArcType.ROUND);
        fixedArc.setFill(Color.GREEN);

        stackPane.getChildren().addAll(circle, freePane, fixedPane);
        fixedPane.getChildren().add(fixedArc);

        Scene scene = new Scene(stackPane, 400, 400);

        Point2D localCircleCenter = stackPane.sceneToLocal(circle.localToScene(new Point2D(circle.getCenterX(), circle.getCenterY())));
        sector.setCenterX(localCircleCenter.getX());  // set the center of the sector to the center of the circle
        sector.setCenterY(localCircleCenter.getY());  // set the center of the sector to the center of the circle
        fixedArc.setCenterX(localCircleCenter.getX());  // set the center of the fixedArc to the center of the circle
        fixedArc.setCenterY(localCircleCenter.getY());  // set the center of the fixedArc to the center of the circle

        stage.setScene(scene);
        stage.show();


        scene.setOnMouseMoved(e -> {


            double angle = Math.atan2(e.getY() - sector.getCenterY(), e.getX() - sector.getCenterX()) * 180 / Math.PI;
            sector.setStartAngle(-angle - 30);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}


