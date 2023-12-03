package com.ace.ucv.controller.table;

import com.ace.ucv.model.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentTableController implements Initializable {

    @FXML
    private TableView<Student> tbStudentData;

    @FXML
    private TableColumn<Student, Integer> studentId;

    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, String> lastName;

    @FXML
    private TableColumn<Student, String> genre;

    @FXML
    private TableColumn<Student, String> phone;

    @FXML
    private AnchorPane root;

    // This method is called automatically when the FXML file is loaded.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    // Initializes the table columns with their respective properties.
    private void initializeTable() {
        studentId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    // Populates the table with the provided list of students.
    public void populateTable(ObservableList<Student> students) {
        tbStudentData.setItems(students);
    }

}
