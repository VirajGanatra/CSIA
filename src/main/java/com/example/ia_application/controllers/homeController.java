package com.example.ia_application.controllers;

import com.example.ia_application.app.event;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;


import java.util.concurrent.atomic.AtomicReference;

public class homeController{
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


    public void initialize() {

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
                System.out.println("inside");
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


    public void addEvent(ActionEvent actionEvent) {
    }

    public boolean insideCircle(Arc arc){

        Point2D localArcCenter = stack.sceneToLocal(arc.localToScene(new Point2D(arc.getCenterX(), arc.getCenterY())));
        Point2D localCircleCenter = stack.sceneToLocal(cal.localToScene(new Point2D(cal.getCenterX(), cal.getCenterY())));

        return localCircleCenter.distance(localArcCenter) + arc.getRadiusX() <= cal.getRadius();

    }


}




