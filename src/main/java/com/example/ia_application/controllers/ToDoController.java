package com.example.ia_application.controllers;

import com.example.ia_application.Driver;
import com.example.ia_application.app.ToDoItem;

import com.example.ia_application.app.ToDoList;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ToDoController implements Initializable {
    @FXML
    private MFXTableView<ToDoItem> table;
    private Stage stage;

    private static ObservableList<ToDoItem> toDoItems = FXCollections.observableArrayList();
    private HomeController homeController;

    public ToDoController(HomeController homeController){
        this.homeController = homeController;
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/ia_application/toDo-view.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            Driver.sceneStack.pushScene(stage.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        table.autosizeColumnsOnInitialization();

    }

    private void setupTable() {
        //table.autosizeColumnsOnInitialization();
        MFXTableColumn<ToDoItem> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(ToDoItem::getName));
        MFXTableColumn<ToDoItem> descColumn = new MFXTableColumn<>("Description", true, Comparator.comparing(ToDoItem::getDescription));
        //MFXTableColumn<ToDoItem> timeColumn = new MFXTableColumn<>("Expected Time", true, Comparator.comparing(ToDoItem::getExpectedTime));
        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ToDoItem::getName));
        descColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ToDoItem::getDescription));
//        surnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ToDoItem::getSurname));
//        ageColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ToDoItem::getAge) {{
//            setAlignment(Pos.CENTER_RIGHT);
//        }});
//        ageColumn.setAlignment(Pos.CENTER_RIGHT);

        table.getTableColumns().addAll(nameColumn, descColumn);
        table.getFilters().addAll(
                new StringFilter<>("Name", ToDoItem::getName),
                new StringFilter<>("Description", ToDoItem::getDescription)
        );
        createToDoItems();
        table.setItems(FXCollections.observableArrayList(toDoItems));

    }

    private void createToDoItems() {
        for (int i = 0; i < HomeController.toDoList.getToDoList().getSize(); i++) {
            toDoItems.add(HomeController.toDoList.getToDoList().get(i));
        }
    }

    public void show() { stage.showAndWait();}
}
