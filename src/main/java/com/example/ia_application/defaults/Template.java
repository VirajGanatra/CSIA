package com.example.ia_application.defaults;

import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.NumberFormat;
import java.util.stream.IntStream;

public class Template {
    public Template(){}
    public static final ObservableList<Integer> hours;
    public static final ObservableList<Integer> minutes;
    public static final ObservableList<String> days;

    static{
        hours = IntStream.rangeClosed(0, 23)
                .boxed()
                .collect(FXCollectors.toList());

        minutes = IntStream.rangeClosed(0, 59)
                .boxed()
                .collect(FXCollectors.toList());
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(2);
        minutes.forEach(i -> {
            if(i < 10){
                minutes.set(i, Integer.parseInt(nf.format(i)));
            }
        });
        days = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    }
}
