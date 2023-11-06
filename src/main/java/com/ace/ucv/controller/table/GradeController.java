package com.ace.ucv.controller.table;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class GradeController implements Initializable {

    @FXML
    private TableView<Grade> tbGradeData;

    @FXML
    private TableColumn<Grade, String> studentName;


    @FXML
    private TableColumn<Grade, String> studentSurname;

    @FXML
    private TableColumn<Grade, String> discipline;

    @FXML
    private TableColumn<Grade, Integer> grade;

    @FXML
    private AnchorPane root;

    private ObservableList<Student> students;
    private ObservableList<Discipline> disciplines;

    public GradeController(ObservableList<Student> students, ObservableList<Discipline> disciplines) {
        this.students = students;
        this.disciplines = disciplines;
    }

    public GradeController() {
        this.disciplines = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    public void setDisciplines(ObservableList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    private void initializeTable() {

        studentName.setCellValueFactory(cellData -> {
            int studentId = cellData.getValue().getStudentId();
            String studentName = getStudentName(studentId);
            return new SimpleStringProperty(studentName);
        });


        studentSurname.setCellValueFactory(cellData -> {
            int studentId = cellData.getValue().getStudentId();
            String studentName = getStudentSurName(studentId);
            return new SimpleStringProperty(studentName);
        });

        discipline.setCellValueFactory(cellData -> {
            int disciplineId = cellData.getValue().getSubjectId();
            String disciplineName = getDisciplineName(disciplineId);
            return new SimpleStringProperty(disciplineName);
        });

        grade.setCellValueFactory(new PropertyValueFactory<>("gradeValue"));

    }

    private String getStudentSurName(int studentId) {

        return students.stream()
                .filter(student -> student.getId() == studentId)
                .map(Student::getSurname)
                .findFirst()
                .orElse("");
    }

    public void populateTable(ObservableList<Grade> grades) {
        tbGradeData.setItems(grades);
    }


    private String getDisciplineName(int disciplineId) {
        System.out.println("Searching for discipline with ID: " + disciplineId);
        for (Discipline discipline : disciplines) {
            System.out.println("Current discipline ID: " + discipline.getId());
            if (discipline.getId() == disciplineId) {
                return discipline.getName();
            }
        }
        return "";
    }

    private String getStudentName(int studentId) {
        return students.stream()
                .filter(student -> student.getId() == studentId)
                .map(Student::getName)
                .findFirst()
                .orElse("");
    }

}
