package com.ace.ucv.controller.table;

import com.ace.ucv.model.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (tbStudentData == null) {
            System.out.println("Elementul TableView (tbStudentData) este nul.");
        }
        if (studentId == null || firstName == null || lastName == null || genre == null || phone == null) {
            System.out.println("Unul sau mai multe elemente din tabel sunt nule.");
        }

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
        // Verificați dacă tbStudentData este null
        if (tbStudentData != null) {
            tbStudentData.setItems(students);
        } else {
            System.err.println("tbStudentData is null");
        }
    }

    public Parent getRoot() {
        return tbStudentData;
    }

}
