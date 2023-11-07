package com.ace.ucv.controller;

import com.ace.ucv.controller.table.DisciplineController;
import com.ace.ucv.controller.table.GradeController;
import com.ace.ucv.controller.table.StudentController;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class MainViewController {

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnDisciplines;

    @FXML
    private Button btnGrades;

    @FXML
    private Button btnCatalog;

    @FXML
    private AnchorPane root;

    private ObservableList<Student> students;
    private ObservableList<Discipline> disciplines;
    private ObservableList<Grade> grades;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == btnStudents) {
            openStudentManagementView();
        } else if (event.getSource() == btnDisciplines) {
            openDisciplineManagementView();
        } else if (event.getSource() == btnGrades) {
            displayData();
            openGradeManagementView();
        } else if (event.getSource() == btnCatalog) {
            generateCatalog();
        }
    }

    private void openStudentManagementView() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                StudentParser studentParser = new StudentParser();
                StudentiType studentData = studentParser.loadConfiguration(selectedFile);

                FXMLLoader loader = new FXMLLoader(new File("D:\\Data\\Facultate\\Master\\An 1\\SEM 1\\MSIC\\Proiect\\StudentManagement\\src\\main\\resources\\views\\student_view.fxml").toURI().toURL());
                root = loader.load();

                StudentController studentController = loader.getController();
                students = adaptStudentData(studentData);
                studentController.populateTable(students);

                Stage stage = new Stage();
                stage.setTitle("Student Information");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException | ConfigurationLoaderException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Student> adaptStudentData(StudentiType studentData) {

        StudentMapper studentMapper = new StudentMapper();
        List<Student> studentList = new ArrayList<>();
        Student student;

        for (StudentType studentType : studentData.getStudent()) {
            student = studentMapper.adaptXmlObjectToStudentIntermediaryObject(studentType);
            studentList.add(student);
        }

        students = FXCollections.observableArrayList(studentList);
        return students;
    }

    private void openDisciplineManagementView() {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {

                DisciplineParser disciplineParser = new DisciplineParser();
                MateriiType materiiType = disciplineParser.loadConfiguration(selectedFile);

                FXMLLoader loader = new FXMLLoader(new File("D:\\Data\\Facultate\\Master\\An 1\\SEM 1\\MSIC\\Proiect\\StudentManagement\\src\\main\\resources\\views\\discipline_view.fxml").toURI().toURL());
                root = loader.load();

                DisciplineController disciplineController = loader.getController();
                disciplines = adaptDisciplineData(materiiType);
                disciplineController.populateTable(disciplines);

                Stage stage = new Stage();
                stage.setTitle("Discipline Information");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (ConfigurationLoaderException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Discipline> adaptDisciplineData(MateriiType materiiType) {
        DisciplineMapper disciplineMapper = new DisciplineMapper();
        List<Discipline> disciplineList = new ArrayList<>();
        Discipline discipline;

        for (MateriaType materiaType : materiiType.getMateria()) {
            discipline = disciplineMapper.adaptXmlObjectToDisciplineIntermediaryObject(materiaType);
            disciplineList.add(discipline);
        }
        return FXCollections.observableArrayList(disciplineList);
    }


    private void openGradeManagementView() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                GradeParser gradeParser = new GradeParser();
                NoteType noteType = gradeParser.loadConfiguration(selectedFile);

                FXMLLoader loader = new FXMLLoader(new File("D:\\Data\\Facultate\\Master\\An 1\\SEM 1\\MSIC\\Proiect\\StudentManagement\\src\\main\\resources\\views\\grade_view.fxml").toURI().toURL());
                root = loader.load();

                GradeController gradeController = loader.getController();
                grades = adaptGradeData(noteType);

                gradeController.setStudents(students);
                gradeController.setDisciplines(disciplines);

                gradeController.populateTable(grades);

                Stage stage = new Stage();
                stage.setTitle("Grade Information");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (ConfigurationLoaderException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Grade> adaptGradeData(NoteType noteType) {
        try {
            System.out.println("Adapting grade data...");
            GradeMapper gradeMapper = new GradeMapper();
            List<Grade> gradeList = new ArrayList<>();
            Grade grade;

            for (NotaStudType gradeType : noteType.getNotaStud()) {
                grade = gradeMapper.adaptXmlObjectToGradeIntermediaryObject(gradeType);
                gradeList.add(grade);
            }

            System.out.println("Grade data adapted successfully.");
            return FXCollections.observableArrayList(gradeList);
        } catch (Exception e) {
            System.err.println("Error adapting grade data: " + e.getMessage());
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }


    private void displayData() {
        System.out.println("Disciplines:");
        disciplines.forEach(discipline -> System.out.println(discipline.getId() + ": " + discipline.getName()));

        System.out.println("Students:");
        students.forEach(student -> System.out.println(student.getId() + ": " + student.getName()));
    }


    private void generateCatalog() {

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

