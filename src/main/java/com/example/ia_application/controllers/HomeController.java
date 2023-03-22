package com.example.ia_application.controllers;

import com.example.ia_application.app.ArcWrapper;
import com.example.ia_application.app.Event;
import com.example.ia_application.app.SingleEvent;
import com.example.ia_application.app.ToDoList;
import com.example.ia_application.app.ToDoItem;
import com.example.ia_application.defaults.DBClass;
import com.example.ia_application.Driver;
import com.example.ia_application.defaults.DBTablePrinter;
import io.github.palexdev.materialfx.controls.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class HomeController {
    private final Stage stage;
    private final com.example.ia_application.controllers.LoginController loginController;
    private Rectangle2D screenBounds;

    public GridPane mainGrid;
    public Pane free;
    public Pane fixed;
    public MFXButton addButton;
    public MFXButton toDoButton;
    public MFXButton reportButton;
    public Arc arc1;
    public Arc arc15;
    public Arc arc5;
    public MFXSlider slide1;
    public MFXSlider slide5;
    public MFXSlider slide15;
    public MFXRadioButton editToggle;
    public MFXRadioButton deleteToggle;
    public MFXDatePicker datePicker;
    public StackPane stack;
    public Circle cal;
    public ArcWrapper newArc;
    public Event currentEvent;
    private HashMap<Arc, MFXSlider> arcSliderMap = new HashMap<>();


    Point2D circleCenter;

    static final ToDoList toDoList = new ToDoList();
    final ArrayList<Arc> arcList = new ArrayList<>();

    public HomeController(LoginController loginController){
        loginController.getStage().close();
        toDoList.addToDoItem(new ToDoItem("Test", "Test", Duration.ofHours(1), true, false, "Test", LocalDate.now().plusDays(1)));
        this.loginController = loginController;
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/home-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            //stage.setMaximized(true);
            Driver.sceneStack.pushScene(stage.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    public void initialize() {


        makeArcDraggable(arc15);
        makeArcDraggable(arc1);
        makeArcDraggable(arc5);
        arcSliderMap.put(arc1, slide1);
        arcSliderMap.put(arc5, slide5);
        arcSliderMap.put(arc15, slide15);

//        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, window -> {
//            loadCalendar(datePicker.getValue());
//        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            loadCalendar(datePicker.getValue());
        });

        addButton.setOnAction(this::addEvent);
        toDoButton.setOnAction(this::addToDo);
        reportButton.setOnAction(this::report);

        editToggle.setOnAction(this::editToggle);
        deleteToggle.setOnAction(this::deleteToggle);



        screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        mainGrid.setMaxSize(screenBounds.getWidth(), screenBounds.getHeight());

        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            System.out.println(oldScene!=null);
            System.out.println(oldScene.getUserData());
            if (oldScene != null && oldScene.getUserData() instanceof AddController) {
                // Navigated from the Two controller
//                if (DBClass.connection == null) {
//                    DBClass.connect();
//                }
                loadCalendar(LocalDate.now());
                System.out.println("Navigated from the Add controller");
            } else {
                // Navigated from the One controller
                System.out.println("Navigated from the OTHER controller");
            }
        });

        Platform.runLater(new Runnable() {
            @Override public void run() {

                loadCalendar(LocalDate.now());
            }
        });
    }

    private void report(ActionEvent actionEvent) {
        ReportController reportController = new ReportController(this);
        reportController.show();

    }



    public void editToggle(ActionEvent actionEvent){
        if (editToggle.isSelected()){
            reset(deleteToggle);
        } else {
            makeArcDraggable(arc1);
            makeArcDraggable(arc5);
            makeArcDraggable(arc15);
            for (Arc arc : arcList){
                moveInside(arc);
            }
        }
    }

    public void deleteToggle(ActionEvent actionEvent){
        if (deleteToggle.isSelected()){
            reset(editToggle);
            System.out.println(arcList);
            for (Arc arc : arcList){
                arc.setOnMouseClicked(event -> {
                    arcList.remove(arc);
                    free.getChildren().remove(arc);
                });
            }
        } else {
            makeArcDraggable(arc1);
            makeArcDraggable(arc5);
            makeArcDraggable(arc15);
            for (Arc arc : arcList){
                moveInside(arc);
            }
        }
    }

    private void reset(MFXRadioButton radio) {
        radio.setSelected(false);
        arc1.setOnMousePressed(null);
        arc1.setOnMouseDragged(null);
        arc1.setOnMouseReleased(null);
        arc5.setOnMousePressed(null);
        arc5.setOnMouseDragged(null);
        arc5.setOnMouseReleased(null);
        arc15.setOnMousePressed(null);
        arc15.setOnMouseDragged(null);
        arc15.setOnMouseReleased(null);
        for (Arc arc : arcList){
            arc.setOnMouseDragged(null);
            arc.setOnMouseReleased(null);
        }
    }


    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(){
        String sql = "SELECT * FROM toDo";
        try{
            Connection connection = DBClass.connection;
            PreparedStatement prst = connection.prepareStatement(sql);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                String due = resultSet.getString("Due Date");
                LocalDate date = LocalDate.parse(due);
                String exp = resultSet.getString("Expected Time");
                Duration time = Duration.parse(exp);
                int imp = resultSet.getInt("Importance");
                boolean importance = (imp == 1);
                int comp = resultSet.getInt("Complete");
                boolean status = (comp == 1);
                String cat = resultSet.getString("Category");
                toDoList.addToDoItem(new ToDoItem(name, description, time, importance, status, cat, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void show(){
        //loadCalendar(LocalDate.now());
        stage.showAndWait();
    }

    public void loadCalendar(LocalDate date){
        long value = date.toEpochDay();

        String singleSQL = "SELECT * FROM single WHERE date = (?)";
        System.out.println(singleSQL);
        try {
            Connection connection = DBClass.connection;
            assert connection != null;
            PreparedStatement prst = connection.prepareStatement(singleSQL);
            //System.out.println(prst);
            //System.out.println(prst.toString());
            prst.setLong(1, value);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()){
                //System.out.println(resultSet.getString("name"));
                ArcWrapper arc = new ArcWrapper();
                makeArcDraggable(arc);
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String start = resultSet.getString("start time");
                String end = resultSet.getString("end time");
                LocalTime startTime = LocalTime.parse(start);
                LocalTime endTime = LocalTime.parse(end);
                Duration duration = Duration.between(startTime, endTime);
                Event current = new SingleEvent(name, description, date, startTime, endTime);
                double startAngle = (((double) startTime.toSecondOfDay() /60) * 0.25)+180;
                double length = (double) duration.toMinutes() * 0.25;
                arc.setCenterX(free.getLayoutX() + circleCenter.getX());
                System.out.println(free.getLayoutX());
                System.out.println(circleCenter.getX());
                arc.setCenterY(free.getLayoutY() + circleCenter.getY());
                arc.setRadiusX(cal.getRadius());
                arc.setRadiusY(cal.getRadius());
                arc.setStartAngle(startAngle);
                arc.setLength(length);
                arc.setEvent(current);
                arc.setFill(Color.GREEN);
                arc.setType(ArcType.ROUND);
                free.getChildren().add(arc);
            }
        } catch (SQLException e) {
            DBClass.connect();
            loadCalendar(date);
        }

        /*String recurringSQL = "SELECT * FROM recurring WHERE date = (?)";
        try (Connection connection = DBClass.connection; PreparedStatement prst = connection.prepareStatement(recurringSQL)){


        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    private void makeArcDraggable(Arc arc) {
        double orgCenterX = arc.getCenterX();
        double orgCenterY = arc.getCenterY();
        AtomicReference<Double> orgSceneX = new AtomicReference<>((double) 0);
        AtomicReference<Double> orgSceneY = new AtomicReference<>((double) 0);
        AtomicReference<Double> orgTranslateX = new AtomicReference<>((double) 0);
        AtomicReference<Double> orgTranslateY = new AtomicReference<>((double) 0);

        Point2D localCircleCenter = stack.sceneToLocal(cal.localToScene(new Point2D(cal.getCenterX(), cal.getCenterY())));
        circleCenter = new Point2D(localCircleCenter.getX(), localCircleCenter.getY());
        System.out.println(circleCenter);

        arc.setOnMousePressed((MouseEvent t) -> {

            orgSceneX.set(t.getSceneX());
            orgSceneY.set(t.getSceneY());
            orgTranslateX.set(((Arc) (t.getSource())).getTranslateX());
            orgTranslateY.set(((Arc) (t.getSource())).getTranslateY());
        });

        arc.setOnMouseDragged((MouseEvent t) -> {
            double offsetX = t.getSceneX() - orgSceneX.get();
            double offsetY = t.getSceneY() - orgSceneY.get();
            double newTranslateX = orgTranslateX.get() + offsetX;
            double newTranslateY = orgTranslateY.get() + offsetY;

            ((Arc) (t.getSource())).setTranslateX(newTranslateX);
            ((Arc) (t.getSource())).setTranslateY(newTranslateY);


        });

        arc.setOnMouseReleased((MouseEvent t) -> {
            arc.setMouseTransparent(true);
            if (insideCircle(arc)) {
                double value = arcSliderMap.get(arc).getValue();
                if (value%15 == 0){
                    value = (value/1440)*360;
                } else {
                    value = (value/24)*360;
                }
                ArcWrapper newArc = genArc(value);
                System.out.println(value);
                newArc.setFill(arc.getFill());
                newArc.setType(ArcType.ROUND);
                free.getChildren().add(newArc);
                System.out.println(free.getLayoutX() + "," + free.getLayoutY());
                System.out.println(circleCenter.getX() + "," + circleCenter.getY());
                /*Arc test = new Arc(free.getLayoutX() + circleCenter.getX(), free.getLayoutY() + circleCenter.getY(), cal.getRadius(), cal.getRadius(), 100, 45);
                test.setFill(Color.GREEN);
                test.setType(ArcType.ROUND);
                fixed.getChildren().add(test);*/
                moveInside(newArc);
            }
            else {
                System.out.println("outside");
            }
            arc.setTranslateX(0);
            arc.setTranslateY(0);
            arc.setMouseTransparent(false);
            makeArcDraggable(arc);

        });

    }

    public ArcWrapper genArc(double value){
        System.out.println(free.getLayoutX() + "," + free.getLayoutY());
        System.out.println(circleCenter.getX() + "," + circleCenter.getY());
        return new ArcWrapper(free.getLayoutX() + circleCenter.getX(), free.getLayoutY() + circleCenter.getY(), cal.getRadius(), cal.getRadius(), 90, value);
    }

    @FXML
    protected void addEvent(ActionEvent event){
        try {
            AddController addController = new AddController(this, datePicker.getValue());
            addController.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addToDo(ActionEvent event){
        try {
            ToDoController addController = new ToDoController(this);
            addController.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insideCircle(Arc arc){
        Point2D localCircleCenter = stack.sceneToLocal(cal.localToScene(new Point2D(cal.getCenterX(), cal.getCenterY())));
        circleCenter = new Point2D(localCircleCenter.getX(), localCircleCenter.getY());

        Point2D localArcCenter = stack.sceneToLocal(arc.localToScene(new Point2D(arc.getCenterX(), arc.getCenterY())));
        return circleCenter.distance(localArcCenter) + arc.getRadiusX() <= cal.getRadius();

    }


    public void moveInside(Arc arc) {
        arcList.add(arc);
        fixed.setMouseTransparent(true);
        arc.setOnMouseClicked((MouseEvent t) -> {
                    System.out.println("clicked");

        });
        arc.setOnMouseDragged((MouseEvent t1) -> {
                double angle = Math.atan2(t1.getY() - cal.getCenterY(), t1.getX() - cal.getCenterX()) * 180 / Math.PI;
                arc.setStartAngle(-angle - 30);

        });

        arc.setOnMouseReleased((MouseEvent t1) -> {
            try {
                currentEvent = new Event();
                currentEvent.setStartTime(LocalTime.MIDNIGHT.plusMinutes((long) ((long) arc.getStartAngle() * 0.25)));
                currentEvent.setDuration(Duration.ofMinutes((long) (arc.getLength() * 0.25)));
                AddDragController addDragController = new AddDragController(this, currentEvent);
                addDragController.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
                fixed.setMouseTransparent(false);
                moveInside(arc);
        });


        }



}




