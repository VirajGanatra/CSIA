package com.example.ia_application.controllers;

import com.example.ia_application.Driver;
import com.example.ia_application.app.ToDoItem;
import com.example.ia_application.defaults.DBClass;
import eu.hansolo.fx.charts.CoxcombChart;
import eu.hansolo.fx.charts.CoxcombChartBuilder;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.data.ChartItemBuilder;
import eu.hansolo.fx.charts.tools.Order;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReportController {
    private final Stage stage;
    private HomeController homeController;
    private ArrayList<ChartItem> items = new ArrayList<>();
    private HashMap<String, Integer> itemMap = new HashMap<>();
    public CoxcombChart chart;
    private int sum = 0;

    public ReportController(HomeController homeController) {
        this.homeController = homeController;
        this.stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/report-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            Driver.sceneStack.pushScene(stage.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.showAndWait();
    }

    public void getData() {
        String sql = "SELECT * from single WHERE Date BETWEEN " + (LocalDate.now().toEpochDay() - 7) + " AND " + LocalDate.now().toEpochDay();
        String name, start, end;
        LocalTime startTime, endTime;
        Duration duration;
        try {
            Connection connection = DBClass.connection;
            PreparedStatement prst = connection.prepareStatement(sql);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("Name");
                start = resultSet.getString("start time");
                end = resultSet.getString("end time");
                startTime = LocalTime.parse(start);
                endTime = LocalTime.parse(end);
                duration = Duration.between(startTime, endTime);
                if (itemMap.containsKey(name)) {
                    itemMap.put(name, itemMap.get(name) + (int) duration.toMinutes() / 10080);
                } else {
                    itemMap.put(name, (int) duration.toMinutes() / 10080);
                }
                items.add(ChartItemBuilder.create().name(name).value(itemMap.get(name)).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        items.add(ChartItemBuilder.create().name("Test").value(23).build());
    }

    EventHandler<MouseEvent> onPressedHandler = e -> {
        Optional<ChartItem> opt = chart.getSelectedItem(e);
        if (opt.isEmpty()) {
            return;
        }
        ChartItem selectedItem = opt.get();
        //System.out.println(selectedItem);
        if (selectedItem.isSelected()) {
            selectedItem.setSelected(false);
        } else {
            items.forEach(item -> item.setSelected(false));
            selectedItem.setSelected(true);
        }
    };

    EventHandler<MouseEvent> onMoveHandler = e -> {
        Optional<ChartItem> opt = chart.getSelectedItem(e);
        if (opt.isEmpty()) {
            return;
        }
        System.out.println(opt.get());
    };

    public void initialize() {
        getData();
        System.out.println(items);
        chart.setItems(items);
        chart.setTextColor(Color.WHITE);
        chart.setAutoTextColor(false);
        chart.setUseChartItemTextFill(false);
        chart.setEqualSegmentAngles(true);
        chart.setOrder(Order.ASCENDING);
        chart.setOnMousePressed(onPressedHandler);
        chart.setOnMouseMoved(onMoveHandler);
        chart.setShowPopup(false);
        chart.setShowItemName(true);
        chart.setFormatString("%.2f");
        chart.setSelectedItemFill(Color.MAGENTA);
//        chart = CoxcombChartBuilder.create()
//                .items(items)
//                .textColor(Color.WHITE)
//                .autoTextColor(false)
//                .useChartItemTextFill(false)
//                .equalSegmentAngles(true)
//                .order(Order.ASCENDING)
//                .onMousePressed(onPressedHandler)
//                .onMouseMoved(onMoveHandler)
//                .showPopup(false)
//                .showItemName(true)
//                .formatString("%.2f")
//                .selectedItemFill(Color.MAGENTA)
//                .build();
        for (int i : itemMap.values()) {
            sum += i;
        }
        chart.addItem(new ChartItem("Unknown", 100 - sum, Color.WHITE));
    }


//    public void start(Stage stage) {
//        ReportController.stage = stage;
////        StackPane pane = new StackPane(chart);
////        pane.setPadding(new Insets(10));
//
//        Scene scene = new Scene(pane);
//
//        stage.setTitle("Coxcomb Chart");
//        stage.setScene(scene);
//        initialize();
//        stage.show();
//

//
//        chart.addItem(new ChartItem("Unknown", 100 - sum, Color.WHITE));
//
//
//    }

}
