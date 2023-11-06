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

public class StudentController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    private void initializeTable() {
        studentId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    public void populateTable(ObservableList<Student> students) {
        tbStudentData.setItems(students);
    }

}
