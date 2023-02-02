package com.example.ia_application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PieChartExample extends Application {
    @Override
    public void start(Stage stage) {
        PieChart pieChart = new PieChart();
        Label label = new Label("Drag a sector onto the pie chart");

        StackPane root = new StackPane();
        root.getChildren().addAll(pieChart, label);

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();

        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            db.setContent(content);
            event.consume();
        });

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
                TextInputDialog dialog = new TextInputDialog("100");
                dialog.setTitle("Percentage");
                dialog.setHeaderText("Enter the percentage of the pie chart to be covered");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    int percentage = Integer.parseInt(result.get());
                    if (pieChart.getData().isEmpty()) {
                        pieChart.getData().add(new PieChart.Data("Data 1", percentage));
                        pieChart.getData().add(new PieChart.Data("Empty Space", 100 - percentage));
                    } else {
                        pieChart.getData().add(new PieChart.Data(db.getString(), percentage));
                    }
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
