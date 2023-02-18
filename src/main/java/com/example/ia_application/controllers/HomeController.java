package com.example.ia_application.controllers;

import com.example.ia_application.app.ArcWrapper;
import com.example.ia_application.app.Event;
import com.example.ia_application.driver;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;

public class HomeController {
    public Pane free;
    public Pane fixed;
    public MFXButton addButton;
    public Arc arc1;
    public Arc arc15;
    public Arc arc5;
    public MFXSlider slide1;
    public MFXSlider slide5;
    public MFXSlider slide15;
    public MFXDatePicker datePicker;
    public StackPane stack;
    public Circle cal;
    public ArcWrapper newArc;
    public Event currentEvent;

    Point2D circleCenter;


    public void initialize() {
            datePicker.setValue(datePicker.getCurrentDate());
            makeArcDraggable(arc15);
            makeArcDraggable(arc1);
            makeArcDraggable(arc5);
            

    }

    private void makeArcDraggable(Arc arc) {
        double orgCenterX = arc.getCenterX();
        double orgCenterY = arc.getCenterY();
        AtomicReference<Double> orgSceneX = new AtomicReference<>((double) 0);
        AtomicReference<Double> orgSceneY = new AtomicReference<>((double) 0);
        AtomicReference<Double> orgTranslateX = new AtomicReference<>((double) 0);
        AtomicReference<Double> orgTranslateY = new AtomicReference<>((double) 0);

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

                ArcWrapper newArc = new ArcWrapper(free.getLayoutX() + circleCenter.getX(), free.getLayoutY() + circleCenter.getY(), cal.getRadius(), cal.getRadius(), 0, 60);
                newArc.setFill(arc.getFill());
                newArc.setType(ArcType.ROUND);
                free.getChildren().add(newArc);
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

    public void changeDate(ActionEvent actionEvent) {
    }


    @FXML
    protected void addEvent(ActionEvent event) throws IOException {
        try {
            AddController addController = new AddController(this);
            addController.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insideCircle(Arc arc){

        Point2D localArcCenter = stack.sceneToLocal(arc.localToScene(new Point2D(arc.getCenterX(), arc.getCenterY())));
        Point2D localCircleCenter = stack.sceneToLocal(cal.localToScene(new Point2D(cal.getCenterX(), cal.getCenterY())));
        circleCenter = new Point2D(localCircleCenter.getX(), localCircleCenter.getY());

        return circleCenter.distance(localArcCenter) + arc.getRadiusX() <= cal.getRadius();

    }


    public void moveInside(Arc arc) {
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




