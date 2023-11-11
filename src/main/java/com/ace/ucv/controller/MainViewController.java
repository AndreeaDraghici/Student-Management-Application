package com.ace.ucv.controller;

import com.ace.ucv.controller.table.DisciplineTableController;
import com.ace.ucv.controller.table.GradeTableController;
import com.ace.ucv.controller.table.StudentTableController;
import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
import com.ace.ucv.model.xml.nota.NotaStudType;
import com.ace.ucv.model.xml.nota.NoteType;
import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.adapter.DisciplineMapper;
import com.ace.ucv.service.adapter.GradeMapper;
import com.ace.ucv.service.adapter.StudentMapper;
import com.ace.ucv.service.exception.ConfigurationLoaderException;
import com.ace.ucv.service.output.CatalogGeneration;
import com.ace.ucv.service.parser.DisciplineParser;
import com.ace.ucv.service.parser.GradeParser;
import com.ace.ucv.service.parser.StudentParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ace.ucv.utils.GUIConstants.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class MainViewController {

    private static final Logger logger = LogManager.getLogger(MainViewController.class);

    @FXML
    public AnchorPane gradeTabContent;

    @FXML
    public AnchorPane disciplineTabContent;

    @FXML
    private AnchorPane studentTabContent;

    @FXML
    private Button studentBtn;

    @FXML
    private Button gradeBtn;

    @FXML
    private Button disciplineBtn;

    @FXML
    private TextField studentTextField;

    @FXML
    private TextField disciplineTextField;

    @FXML
    private TextField gradeTextField;

    @FXML
    private Tab studentTab;

    @FXML
    private Tab disciplineTab;

    @FXML
    public Tab gradeTab;

    @FXML
    private Button generateBtn;

    @FXML
    AnchorPane root;

    private ObservableList<Student> students;
    private ObservableList<Discipline> disciplines;
    private ObservableList<Grade> grades;

    public MainViewController() {
    }


    private void initializeTabs() {
        getStudentTabContent();
        getDisciplineTabContent();
        getGradeTabContent();
    }

    private void getGradeTabContent() {
        if (gradeTabContent != null) {
            gradeTab.setContent(gradeTabContent);
        } else {
            throw new RuntimeException("TabGrade must have the associated table of grades information.");
        }
    }

    private void getDisciplineTabContent() {
        if (disciplineTabContent != null) {
            disciplineTab.setContent(disciplineTabContent);
        } else {
            throw new RuntimeException("TabDiscipline must have the associated table of disciplines information.");
        }
    }

    private void getStudentTabContent() {
        if (studentTabContent != null) {
            studentTab.setContent(studentTabContent);
        } else {
            throw new RuntimeException("TabStudent must have the associated table of students information.");
        }
    }

    private void loadFile(TextField textField, String fileType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(String.format("Select %s File", fileType));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            textField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void handleStudentButton() {

        try {
            loadFile(studentTextField, "Student");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(STUDENT_VIEW_FXML));
            root = loader.load();

            StudentTableController studentController = loader.getController();
            students = loadStudentDataFromFile(studentTextField.getText());
            studentController.populateTable(students);

            studentTabContent.getChildren().clear();
            studentTabContent.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Student> loadStudentDataFromFile(String text) {
        try {
            StudentParser studentParser = new StudentParser();
            StudentiType studentData = studentParser.loadConfiguration(new File(text));

            StudentMapper studentMapper = new StudentMapper();
            List<Student> studentList = new ArrayList<>();
            Student student;

            for (StudentType studentType : studentData.getStudent()) {
                student = studentMapper.adaptXmlObjectToStudentIntermediaryObject(studentType);
                studentList.add(student);
            }

            logger.info("Students information loaded successfully.");

            students = FXCollections.observableArrayList(studentList);
            return students;

        } catch (ConfigurationLoaderException e) {
            logger.error(String.format("Error loading students information from input file due to: %s", e.getMessage()));
            return FXCollections.emptyObservableList();
        }
    }

    @FXML
    private void handleDisciplineButton() {
        try {
            loadFile(disciplineTextField, "Discipline");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(DISCIPLINE_VIEW_FXML));
            root = loader.load();

            DisciplineTableController disciplineTableController = loader.getController();
            disciplines = loadDisciplineDataFromFile(disciplineTextField.getText());
            disciplineTableController.populateTable(disciplines);

            disciplineTabContent.getChildren().clear();
            disciplineTabContent.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Discipline> loadDisciplineDataFromFile(String text) {
        try {
            DisciplineParser disciplineParser = new DisciplineParser();
            MateriiType disciplineData = disciplineParser.loadConfiguration(new File(text));

            DisciplineMapper disciplineMapper = new DisciplineMapper();
            List<Discipline> disciplineList = new ArrayList<>();
            Discipline discipline;

            for (MateriaType materiaType : disciplineData.getMateria()) {
                discipline = disciplineMapper.adaptXmlObjectToDisciplineIntermediaryObject(materiaType);
                disciplineList.add(discipline);
            }

            logger.info("Disciplines information loaded successfully.");

            disciplines = FXCollections.observableArrayList(disciplineList);
            return disciplines;

        } catch (ConfigurationLoaderException e) {
            logger.error(String.format("Error loading disciplines information from input file due to: %s", e.getMessage()));
            return FXCollections.emptyObservableList();
        }
    }

    @FXML
    private void handleGradeButton() {
        try {
            loadFile(gradeTextField, "Grade");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(GRADE_VIEW_FXML));
            root = loader.load();

            GradeTableController gradeTableController = loader.getController();
            grades = loadGradeDataFromFile(gradeTextField.getText());

            gradeTableController.setStudents(students);
            gradeTableController.setDisciplines(disciplines);

            gradeTableController.populateTable(grades);

            gradeTabContent.getChildren().clear();
            gradeTabContent.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Grade> loadGradeDataFromFile(String text) {
        try {
            GradeParser gradeParser = new GradeParser();
            NoteType gradeData = gradeParser.loadConfiguration(new File(text));

            GradeMapper gradeMapper = new GradeMapper();
            List<Grade> gradeList = new ArrayList<>();
            Grade grade;

            for (NotaStudType notaStudType : gradeData.getNotaStud()) {
                grade = gradeMapper.adaptXmlObjectToGradeIntermediaryObject(notaStudType);
                gradeList.add(grade);
            }

            logger.info("Grades information loaded successfully.");

            grades = FXCollections.observableArrayList(gradeList);
            return grades;

        } catch (ConfigurationLoaderException e) {
            logger.error(String.format("Error loading grades information from input file due to: %s", e.getMessage()));
            return FXCollections.emptyObservableList();
        }
    }

    @FXML
    private void handleGenerateButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose output directory and save the report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File file = fileChooser.showSaveDialog(root.getScene().getWindow());

        try {
            Catalog catalog = new Catalog();
            catalog.setDisciplines(disciplines);
            catalog.setStudents(students);
            catalog.setGrades(grades);

            CatalogGeneration generation = new CatalogGeneration();
            generation.generateXMLCatalog(catalog, String.valueOf(file));

        } catch (Exception exception) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(root.getScene().getWindow());
            alert.setTitle("Warning Dialog Box");
            alert.setHeaderText("Warning");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }

    }
}