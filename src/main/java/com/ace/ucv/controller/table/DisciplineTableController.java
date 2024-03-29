package com.ace.ucv.controller.table;

import com.ace.ucv.model.Discipline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class DisciplineTableController implements Initializable {

    @FXML
    public TableView<Discipline> tbDisciplineData;

    @FXML
    public TableColumn<Discipline, Integer> disciplineId;

    @FXML
    public TableColumn<Discipline, String> firstName;

    @FXML
    public TableColumn<Discipline, String> teacher;

    @FXML
    public TableColumn<Discipline, String> year;

    @FXML
    public TableColumn<Discipline, String> semester;

    // Initialization method called automatically when the FXML file is loaded.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    // Initializes the table columns with their respective properties.
    private void initializeTable() {
        disciplineId.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
    }

    // Populates the table with the provided list of disciplines.
    public void populateTable(ObservableList<Discipline> disciplines) {
        tbDisciplineData.setItems(disciplines);
    }
}
