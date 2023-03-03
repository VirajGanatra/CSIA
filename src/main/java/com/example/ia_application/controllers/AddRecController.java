package com.example.ia_application.controllers;

import com.example.ia_application.app.RecurringEvent;
import com.example.ia_application.defaults.Template;
import com.example.ia_application.Driver;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.selection.base.IMultipleSelectionModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddRecController {
    public MFXButton back;
    public MFXButton add;
    public MFXCheckListView<String> dow;
    public MFXDatePicker startDate;
    public MFXDatePicker endDate;

    private final Stage stage;
    private final AddController addController;

    public AddRecController(AddController addController){
        this.addController = addController;
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/addRecurring-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            Driver.sceneStack.pushScene(stage.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        ObservableList<String> days = Template.days;
        dow.setItems(days);
        ButtonBar.setButtonData(back, ButtonBar.ButtonData.LEFT);
        add.setOnAction(this::addEvent);
        back.setOnAction(this::back); //method reference used for readability; clear to see what method is being called!
    }

    public void back(ActionEvent actionEvent) {
    }

    public void addEvent(ActionEvent actionEvent) {
        RecurringEvent currentEvent = new RecurringEvent();

        currentEvent.setName(addController.name.getText());
        currentEvent.setDescription(addController.desc.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");


        currentEvent.setStartTime(LocalTime.parse(addController.startH.getValue().toString() + ":" + addController.startM.getValue().toString(), formatter));
        currentEvent.setEndTime(LocalTime.parse(addController.endH.getValue().toString() + ":" + addController.endM.getValue().toString(), formatter));
        currentEvent.setDuration();
        currentEvent.setStartDate(startDate.getValue());
        currentEvent.setEndDate(endDate.getValue());


        IMultipleSelectionModel<String> selectionModel = dow.getSelectionModel();
        List<String> selectedItems = selectionModel.getSelectedValues();
        String selectedDays = String.join(",", selectedItems);
        currentEvent.setDaysOfWeek(selectedDays);


        currentEvent.addToDB();
    }

    public void show() {
        stage.showAndWait();
    }
}
