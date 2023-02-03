package com.example.ia_application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class test extends Application {
    private double startAngle;
    private PieChart chart;
    private ObservableList<PieChart.Data> chartData;

    @Override
    public void start(Stage primaryStage) {
        chart = new PieChart();
        chart.setPrefSize(300, 300);
        StackPane chartPane = new StackPane(chart);

        Arc sector = new Arc(150, 150, 140, 140, 0, 0);
        sector.setFill(Color.LIGHTGRAY);
        sector.setType(ArcType.ROUND);

        Text text = new Text("0%");
        text.setFill(Color.BLACK);
        text.setTranslateX(70);
        text.setTranslateY(85);

        StackPane sectorPane = new StackPane(sector, text);

        StackPane root = new StackPane(chartPane, sectorPane);
        Scene scene = new Scene(root, 600, 600);

        sectorPane.setOnMousePressed(e -> handleMousePressed(e));
        sectorPane.setOnMouseDragged(e -> handleMouseDragged(e));
        sectorPane.setOnMouseReleased(e -> handleMouseReleased(e));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double getStartAngle(Point2D mouseSceneCoords) {
        double centerX = chart.getLayoutX() + chart.getPrefWidth() / 2;
        double centerY = chart.getLayoutY() + chart.getPrefHeight() / 2;
        double radius = chart.getPrefWidth() / 2;

        double x = mouseSceneCoords.getX() - centerX;
        double y = mouseSceneCoords.getY() - centerY;

        return Math.toDegrees(Math.atan2(y, x)) + 90;
    }


    private void handleMousePressed(MouseEvent e) {
        Point2D mouseSceneCoords = new Point2D(e.getSceneX(), e.getSceneY());
        startAngle = getStartAngle(mouseSceneCoords);
    }

    private void handleMouseDragged(MouseEvent e) {
        Point2D mouseSceneCoords = new Point2D(e.getSceneX(), e.getSceneY());
        double angle = getStartAngle(mouseSceneCoords);
        double newPercentage = angle / 360;

        // Check if the mouse is over the chart or outside of it
        if (chart.contains(mouseSceneCoords)) {
            chartData.get(0).setPieValue(100 * newPercentage);
        } else {
            chartData.add(new PieChart.Data("White Space", 100 * (1 - newPercentage)));
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        Point2D mouseSceneCoords = new Point2D(e.getSceneX(), e.getSceneY());
        if (!chart.contains(mouseSceneCoords)) {
            double angle = getStartAngle(mouseSceneCoords);
            double newPercentage = angle / 360;
            chartData.add(new PieChart.Data("New Slice", 100 * newPercentage));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
