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
import com.ace.ucv.service.output.poperties.PropertiesHandler;
import com.ace.ucv.service.output.poperties.PropertiesModel;
import com.ace.ucv.service.output.poperties.iface.IProperties;
import com.ace.ucv.service.parser.DisciplineParser;
import com.ace.ucv.service.parser.GradeParser;
import com.ace.ucv.service.parser.StudentParser;
import com.ace.ucv.service.parser.iface.IConfigurationLoader;
import com.ace.ucv.utils.AlertCreator;
import com.ace.ucv.utils.PathChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ace.ucv.utils.ApplicationPropertiesConstants.APPLICATION_PROPERTIES;
import static com.ace.ucv.utils.GUIConstants.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class MainViewController {

    private static final Logger logger = LogManager.getLogger(MainViewController.class);

    public static final String XML = ".xml";

    @FXML
    public AnchorPane gradeTabContent;

    @FXML
    public AnchorPane disciplineTabContent;

    @FXML
    private AnchorPane studentTabContent;

    @FXML
    private Button loadStudentButton;

    @FXML
    private Button loadGradeButton;

    @FXML
    private Button loadDisciplineButton;

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
    private Button generateCatalogButton;

    @FXML
    AnchorPane root;

    private ObservableList<Student> students;
    private ObservableList<Discipline> disciplines;
    private ObservableList<Grade> grades;

    private boolean isStudentFileLoaded = false;
    private boolean isDisciplineFileLoaded = false;
    private boolean isGradeFileLoaded = false;

    private final AlertCreator creator;

    private final IProperties propertiesHandler; // Use the interface
    private final IConfigurationLoader<StudentiType> studentParser; // Use the interface
    private final IConfigurationLoader<MateriiType> disciplineParser; // Use the interface
    private final IConfigurationLoader<NoteType> gradeParser; // Use the interface

    public MainViewController() {
        this.creator = new AlertCreator();
        this.propertiesHandler = new PropertiesHandler();
        this.studentParser = new StudentParser();
        this.disciplineParser = new DisciplineParser();
        this.gradeParser = new GradeParser();
    }

    // Initializes the controller.
    public void initialize() {
        initializeTabs();
        loadApplicationProperties();
        generateCatalogButton.setDisable(true);
    }

    // Initializes the tabs with their respective content.
    private void initializeTabs() {
        getStudentTabContent();
        getDisciplineTabContent();
        getGradeTabContent();
        checkGenerateButton();
    }

    // Retrieves the content for the Grade tab.
    private void getGradeTabContent() {
        if (gradeTabContent != null) {
            gradeTab.setContent(gradeTabContent);
        } else {
            throw new RuntimeException("TabGrade must have the associated table of grades information.");
        }
    }

    // Retrieves the content for the Discipline tab.
    private void getDisciplineTabContent() {
        if (disciplineTabContent != null) {
            disciplineTab.setContent(disciplineTabContent);
        } else {
            throw new RuntimeException("TabDiscipline must have the associated table of disciplines information.");
        }
    }

    // Retrieves the content for the Student tab.
    private void getStudentTabContent() {
        if (studentTabContent != null) {
            studentTab.setContent(studentTabContent);
        } else {
            throw new RuntimeException("TabStudent must have the associated table of students information.");
        }
    }

    // Handles the loading of input file.
    private boolean loadInputFile(TextField textField, String fileType) {
        PathChooser chooser = new PathChooser();
        FileChooser fileChooser = chooser.getFileChooser(fileType);

        File lastUsed = new File(textField.getText());
        chooser.setInitialDirectoryIfExists(fileChooser, lastUsed);

        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (selectedFile == null) {
            showWarningModal(String.format("No input selected for %s file.", fileType));
            return true;
        }

        if (!selectedFile.toString().endsWith(XML)) {
            showWarningModal("Select a valid input file! Input file must have the .xml extension!");
            return true;
        }

        textField.setText(selectedFile.getAbsolutePath());
        return false;
    }


    // Handles the button click to load student information.
    @FXML
    private void handleStudentButton() {

        try {
            if (loadInputFile(studentTextField, "Student")) {
                return;
            }

            FXMLLoader loader = loadFXML(STUDENT_VIEW_FXML);
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

    // Loads student data from an XML file.
    private ObservableList<Student> loadStudentDataFromFile(String text) {
        try {
            studentParser.inputCheck(new File(text));
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
            showErrorModal(string);
            logger.error(string);
            return FXCollections.emptyObservableList();
        }
    }

    // Handles the button click to load discipline information.
    @FXML
    private void handleDisciplineButton() {
        try {
            if (loadInputFile(disciplineTextField, "Discipline")) {
                return;
            }

            FXMLLoader loader = loadFXML(DISCIPLINE_VIEW_FXML);

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

    // Loads discipline data from an XML file.
    private ObservableList<Discipline> loadDisciplineDataFromFile(String text) {
        try {
            disciplineParser.inputCheck(new File(text));
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
            showErrorModal(string);
            logger.error(string);
            return FXCollections.emptyObservableList();
        }
    }

    // Handles the button click to load grade information.
    @FXML
    private void handleGradeButton() {
        try {
            if (loadInputFile(gradeTextField, "Grade")) {
                return;
            }

            FXMLLoader loader = loadFXML(GRADE_VIEW_FXML);

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

    // Loads grade data from an XML file.
    private ObservableList<Grade> loadGradeDataFromFile(String text) {
        try {
            gradeParser.inputCheck(new File(text));
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
            showErrorModal(string);
            logger.error(string);
            return FXCollections.emptyObservableList();
        }
    }

    // Checks if all necessary files are loaded to enable the generate button.
    private void checkGenerateButton() {
        generateCatalogButton.setDisable(!(isStudentFileLoaded && isDisciplineFileLoaded && isGradeFileLoaded));
    }

    // Handles the button click to generate the output catalog.
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

            showInformationModal(String.format("Catalog was generated with succes! Check output path: %s ", file.getPath()));
            saveApplicationProperties();

        } catch (Exception exception) {
            String string = String.format("Failed to generate the output catalog due to: %s", exception.getMessage());
            showErrorModal(string);
            logger.error(string);
        }
    }

    /**
     * Loads application properties from the specified file and updates the UI components accordingly.
     * If the properties file does not exist, no action is taken.
     */
    private void loadApplicationProperties() {
        try {
            File file = new File(APPLICATION_PROPERTIES);

            if (!file.exists()) {
                return;
            }

            PropertiesModel model = propertiesHandler.loadProperties(file);

            updateUIComponentsWithLoadedProperties(model);
            logger.info("Loaded  application properties successfully.");

        } catch (Exception e) {
            String string = String.format("Could not load application properties due to: %s", e.getMessage());
            showErrorModal(string);
            logger.error(string);
        }
    }


    /**
     * Sets the application properties in the UI based on the provided PropertiesModel.
     *
     * @param model The PropertiesModel containing the application properties.
     */
    private void updateUIComponentsWithLoadedProperties(PropertiesModel model) {
        if (new File(model.getStudentPath()).exists()) {
            studentTextField.setText(model.getStudentPath());
        }

        if (new File(model.getDisciplinePath()).exists()) {
            disciplineTextField.setText(model.getDisciplinePath());
        }

        if (new File(model.getGradePath()).exists()) {
            gradeTextField.setText(model.getGradePath());
        }
    }


    /**
     * Saves the current state of UI components as application properties to the specified file.
     */
    private void saveApplicationProperties() {
        try {

            PropertiesModel model = new PropertiesModel();
            model.setGradePath(gradeTextField.getText());
            model.setDisciplinePath(disciplineTextField.getText());
            model.setStudentPath(studentTextField.getText());

            File file = new File(APPLICATION_PROPERTIES);

            if (!file.exists()) {
                file.createNewFile();
            }

            propertiesHandler.saveProperties(model, file);
            logger.info("Saved application properties successfully.");

        } catch (Exception e) {
            String string = String.format("Failed to save application properties due to: %s", e.getMessage());
            showErrorModal(string);
            logger.error(string);
        }
    }

    /**
     * Shows an error modal with the specified error message.
     *
     * @param errorMessage The error message to display in the modal.
     */
    private void showErrorModal(String errorMessage) {
        creator.createErrorModal(root, errorMessage);
    }


    /**
     * Shows a warning modal with the specified warning message.
     *
     * @param warningMessage The warning message to display in the modal.
     */
    private void showWarningModal(String warningMessage) {
        creator.createWarningModal(root, warningMessage);
    }


    /**
     * Shows an information modal with the specified information message.
     *
     * @param informationMessage The information message to display in the modal.
     */
    private void showInformationModal(String informationMessage) {
        creator.createInformationModal(root, informationMessage);
    }


    /**
     * Loads an FXML file and returns the FXMLLoader instance.
     *
     * @param fxmlPath The path to the FXML file.
     * @return The FXMLLoader instance after loading the FXML file.
     * @throws IOException If an I/O error occurs during loading.
     */
    private FXMLLoader loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        root = loader.load();

        return loader;
    }
}