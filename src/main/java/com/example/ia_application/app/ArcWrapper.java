package com.example.ia_application.app;

import javafx.scene.shape.Arc;

public class ArcWrapper extends Arc {

    private Event event;

    public ArcWrapper(double centerX, double centerY, double radiusX, double radiusY,
                    double startAngle, double length, Event event) {
        super(centerX, centerY, radiusX, radiusY, startAngle, length);
        this.event = event;
    }

    public ArcWrapper(double centerX, double centerY, double radiusX, double radiusY,
                      double startAngle, double length) {
        super(centerX, centerY, radiusX, radiusY, startAngle, length);
    }

    public ArcWrapper() {
        super();
    }

    public ArcWrapper(Arc arc) {
        super(arc.getCenterX(), arc.getCenterY(), arc.getRadiusX(), arc.getRadiusY(), arc.getStartAngle(), arc.getLength());
    }

    public ArcWrapper(Arc arc, Event event) {
        super(arc.getCenterX(), arc.getCenterY(), arc.getRadiusX(), arc.getRadiusY(), arc.getStartAngle(), arc.getLength());
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
