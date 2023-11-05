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
public class DisciplineController  implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    private void initializeTable() {
        disciplineId.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
    }


    public void populateTable(ObservableList<Discipline> disciplines) {

        tbDisciplineData.setItems(disciplines);
    }
}
