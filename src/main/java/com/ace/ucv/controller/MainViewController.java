package com.ace.ucv.controller;

import com.ace.ucv.controller.table.DisciplineTableController;
import com.ace.ucv.controller.table.GradeTableController;
import com.ace.ucv.controller.table.StudentTableController;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.adapter.DisciplineMapper;
import com.ace.ucv.service.adapter.StudentMapper;
import com.ace.ucv.service.exception.ConfigurationLoaderException;
import com.ace.ucv.service.parser.StudentParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ace.ucv.utils.GUIConstants.STUDENT_VIEW_FXML;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class MainViewController {

    private static final Logger logger = LogManager.getLogger(MainViewController.class);

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

    private ObservableList<Discipline> disciplines;
    private ObservableList<Grade> grades;


    public MainViewController() {
    }


    private void initializeTabs() {
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
            ObservableList<Student> studentData = loadDataFromFile(studentTextField.getText());
            studentController.populateTable(studentData);

            studentTabContent.getChildren().clear();
            studentTabContent.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Student> loadDataFromFile(String text) {
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
            return FXCollections.observableArrayList(studentList);
        } catch (ConfigurationLoaderException e) {
            logger.error(String.format("Error loading students information from input file due to: %s", e.getMessage()));
            return FXCollections.emptyObservableList();
        }
    }

    @FXML
    private void handleDisciplineButton() {
        loadFile(disciplineTextField, "Discipline");
    }

    @FXML
    private void handleGradeButton() {
        loadFile(gradeTextField, "Grade");
    }

}