package com.example.ia_application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;

import java.util.Optional;

public class tester extends Application {

    @Override
    public void start(Stage stage) {
        PieChart pieChart = new PieChart();
        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(false);

        Label label = new Label();
        label.setText("Add\nSector");
        label.setStyle("-fx-background-color: white;");
        label.setMinSize(50, 50);
        label.setMaxSize(50, 50);
        label.setPrefSize(50, 50);
        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            db.setContent(content);
            event.consume();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(pieChart, label);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

        pieChart.setOnDragOver(event -> {
            if (event.getGestureSource() != pieChart &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        pieChart.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Point2D point = pieChart.sceneToLocal(event.getSceneX(), event.getSceneY());
                double radialPos = Math.atan2(point.getY(), point.getX());
                double totalPercentage = pieChart.getData().stream().mapToDouble(PieChart.Data::getPieValue).sum();
                double newPercentage = 100 - totalPercentage;
                double startAngle = -radialPos * 180 / Math.PI + 90;
                double length = 360 * newPercentage / 100;

                pieChart.getData().add(new PieChart.Data("", newPercentage));
                PieChart.Data newData = pieChart.getData().get(pieChart.getData().size() - 1);
                newData.getNode().setRotate(startAngle);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });


        pieChart.setOnMouseClicked(event -> {
            Point2D point = pieChart.sceneToLocal(event.getSceneX(), event.getSceneY());
            double angle = Math.toDegrees(Math.atan2(point.getY(), point.getX()));
            TextInputDialog dialog = new TextInputDialog("100");
            dialog.setTitle("Percentage");
            dialog.setHeaderText("Enter the percentage of the pie chart to be covered");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                double percentage = Double.parseDouble(result.get());
                double startAngle = angle - 90;
                if (startAngle < 0) {
                    startAngle += 360;
                }

                pieChart.getData().add(new PieChart.Data("", percentage));
                PieChart.Data newData = pieChart.getData().get(pieChart.getData().size() - 1);
                newData.getNode().setRotate(startAngle+90);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}


