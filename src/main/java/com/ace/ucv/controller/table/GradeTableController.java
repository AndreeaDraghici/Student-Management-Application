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
public class GradeTableController implements Initializable {

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

    // Constructor with parameters for initializing students and disciplines.
    public GradeTableController(ObservableList<Student> students, ObservableList<Discipline> disciplines) {
        this.students = students;
        this.disciplines = disciplines;
    }

    // Default constructor with empty observable lists.
    public GradeTableController() {
        this.disciplines = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
    }

    // Setter for students.
    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    // Setter for disciplines.
    public void setDisciplines(ObservableList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }


    // Initialization method called automatically when the FXML file is loaded.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    // Initializes the table columns with their respective properties.
    private void initializeTable() {
        studentName.setCellValueFactory(this::getStudentNameProperty);
        studentSurname.setCellValueFactory(this::getStudentSurnameProperty);
        discipline.setCellValueFactory(this::getDisciplineProperty);
        grade.setCellValueFactory(new PropertyValueFactory<>("gradeValue"));
    }


    /**
     * Retrieves the discipline name for a given Grade.
     * @param cellData The CellDataFeatures containing information about the Grade.
     * @return A SimpleStringProperty containing the discipline name.
     */
    private SimpleStringProperty getDisciplineProperty(TableColumn.CellDataFeatures<Grade, String> cellData) {
        String disciplineName = getDisciplineName(cellData.getValue().getSubjectId());
        return new SimpleStringProperty(disciplineName);
    }


    /**
     * Retrieves the student's surname for a given Grade.
     * @param cellData The CellDataFeatures containing information about the Grade.
     * @return A SimpleStringProperty containing the student's surname.
     */
    private SimpleStringProperty getStudentSurnameProperty(TableColumn.CellDataFeatures<Grade, String> cellData) {
        String studentName = getStudentSurName(cellData.getValue().getStudentId());
        return new SimpleStringProperty(studentName);
    }

    /**
     * Retrieves the student's name for a given Grade.
     * @param cellData The CellDataFeatures containing information about the Grade.
     * @return A SimpleStringProperty containing the student's name.
     */
    private SimpleStringProperty getStudentNameProperty(TableColumn.CellDataFeatures<Grade, String> cellData) {
        String studentName = getStudentName(cellData.getValue().getStudentId());
        return new SimpleStringProperty(studentName);
    }

    // Populates the table with the provided list of grades.
    public void populateTable(ObservableList<Grade> grades) {
        tbGradeData.setItems(grades);
    }

    // Helper method to get the surname of a student based on the student ID.
    private String getStudentSurName(int studentId) {

        return students.stream()
                .filter(student -> student.getId() == studentId)
                .map(Student::getSurname)
                .findFirst()
                .orElse("");
    }

    // Helper method to get the name of a discipline based on the discipline ID.
    private String getDisciplineName(int disciplineId) {
        return disciplines.stream()
                .filter(discipline -> discipline.getId() == disciplineId)
                .map(Discipline::getName)
                .findFirst()
                .orElse("");
    }

    // Helper method to get the name of a student based on the student ID.
    private String getStudentName(int studentId) {
        return students.stream()
                .filter(student -> student.getId() == studentId)
                .map(Student::getName)
                .findFirst()
                .orElse("");
    }

}