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
    Integer count = 0;
    PieChart pieChart = new PieChart();
    @Override
    public void start(Stage stage) {

        Label label = new Label("Drag a sector onto the pie chart");
        pieChart.setLabelsVisible(false);


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
                    if (pieChart.getData().size() > 0) {
                        for (PieChart.Data data : pieChart.getData()) {
                            pieChart.getData().add(new PieChart.Data("Data " + count.toString(), percentage));
                            data.setPieValue(100*(100-percentage)/data.getPieValue());
                        }
                    } else {
                        pieChart.getData().add(new PieChart.Data("Empty Space", 100));
                    }
                    count++;
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public int sum(PieChart pieChart) {
        int sum = 0;
        for (PieChart.Data data : pieChart.getData()) {
            sum += data.getPieValue();
        }
        return sum;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
