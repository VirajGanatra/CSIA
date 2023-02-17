package com.example.ia_application.app;

import javafx.scene.shape.Arc;

public class ArcWrapper extends Arc {

    private event event;

    public ArcWrapper(double centerX, double centerY, double radiusX, double radiusY,
                    double startAngle, double length, event event) {
        super(centerX, centerY, radiusX, radiusY, startAngle, length);
        this.event = event;
    }

    public ArcWrapper(double centerX, double centerY, double radiusX, double radiusY,
                      double startAngle, double length) {
        super(centerX, centerY, radiusX, radiusY, startAngle, length);
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }
}
