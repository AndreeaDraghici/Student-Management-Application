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
import com.ace.ucv.utils.AlertCreator;
import com.ace.ucv.utils.PathChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.ace.ucv.utils.GUIConstants.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class MainViewController implements Initializable {

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

    private boolean isStudentFileLoaded = false;
    private boolean isDisciplineFileLoaded = false;
    private boolean isGradeFileLoaded = false;
    private final AlertCreator creator;

    public MainViewController() {
        this.creator = new AlertCreator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTabs();
        generateBtn.setDisable(true);
    }

    private void initializeTabs() {
        getStudentTabContent();
        getDisciplineTabContent();
        getGradeTabContent();
        checkGenerateButton();
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

    private boolean loadInputFile(TextField textField, String fileType) {

        PathChooser chooser = new PathChooser();
        FileChooser fileChooser = chooser.getFileChooser(fileType);

        File lastUsed = new File(textField.getText());
        if (lastUsed.exists()) {
            fileChooser.setInitialDirectory(new File(lastUsed.getParent()));
        }

        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (selectedFile == null) {
            String errorMessage = String.format("No input selected for %s file.", fileType);
            creator.createErrorModal(root, errorMessage);
            return true;
        }

        if (!selectedFile.toString().endsWith(".xml")) {
            String errorMessage = "Select a valid input file! Input file must have the .xml extension!";
            creator.createErrorModal(root, errorMessage);
            return true;
        }
        textField.setText(selectedFile.getAbsolutePath());
        return false;
    }

    @FXML
    private void handleStudentButton() {

        try {
            if (loadInputFile(studentTextField, "Student")) {
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(STUDENT_VIEW_FXML));
            root = loader.load();

            StudentTableController studentController = loader.getController();

            students = loadStudentDataFromFile(studentTextField.getText());
            studentController.populateTable(students);

            studentTabContent.getChildren().clear();
            studentTabContent.getChildren().add(root);

            isStudentFileLoaded = true;
            checkGenerateButton();

        } catch (IOException e) {
            isStudentFileLoaded = false;
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
            String string = String.format("Error loading students information from input file due to: %s", e.getMessage());
            creator.createErrorModal(root, string);
            logger.error(string);
            return FXCollections.emptyObservableList();
        }
    }

    @FXML
    private void handleDisciplineButton() {
        try {
            if (loadInputFile(disciplineTextField, "Discipline")) {
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(DISCIPLINE_VIEW_FXML));
            root = loader.load();

            DisciplineTableController disciplineTableController = loader.getController();
            disciplines = loadDisciplineDataFromFile(disciplineTextField.getText());
            disciplineTableController.populateTable(disciplines);

            disciplineTabContent.getChildren().clear();
            disciplineTabContent.getChildren().add(root);

            isDisciplineFileLoaded = true;
            checkGenerateButton();

        } catch (IOException e) {
            isDisciplineFileLoaded = false;
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
            String string = String.format("Error loading disciplines information from input file due to: %s", e.getMessage());
            creator.createErrorModal(root, string);
            logger.error(string);
            return FXCollections.emptyObservableList();
        }
    }

    @FXML
    private void handleGradeButton() {
        try {
            if (loadInputFile(gradeTextField, "Grade")) {
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(GRADE_VIEW_FXML));
            root = loader.load();

            GradeTableController gradeTableController = loader.getController();
            grades = loadGradeDataFromFile(gradeTextField.getText());

            gradeTableController.setStudents(students);
            gradeTableController.setDisciplines(disciplines);

            gradeTableController.populateTable(grades);

            gradeTabContent.getChildren().clear();
            gradeTabContent.getChildren().add(root);

            isGradeFileLoaded = true;
            checkGenerateButton();

        } catch (IOException e) {
            isGradeFileLoaded = false;
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
            String string = String.format("Error loading grades information from input file due to: %s", e.getMessage());
            creator.createErrorModal(root, string);
            logger.error(string);
            return FXCollections.emptyObservableList();
        }
    }

    private void checkGenerateButton() {
        generateBtn.setDisable(!(isStudentFileLoaded && isDisciplineFileLoaded && isGradeFileLoaded));
    }

    @FXML
    private void handleGenerateButton() {
        try {
            PathChooser chooser = new PathChooser();
            File file = chooser.chooseOuputDirectoryAndSaveTheCatalogFile(root);
            if (file == null) {
                throw new RuntimeException("Please specify the output file name and directory.");
            }

            Catalog catalog = new Catalog();
            catalog.setDisciplines(disciplines);
            catalog.setStudents(students);
            catalog.setGrades(grades);

            CatalogGeneration generation = new CatalogGeneration();
            generation.generateXMLCatalog(catalog, String.valueOf(file));

        } catch (Exception exception) {
            String string = String.format("Failed to generate the output catalog due to: %s", exception.getMessage());
            creator.createWarningModal(root, string);
            logger.error(string);
        }
    }
}