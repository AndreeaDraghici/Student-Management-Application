package com.ace.ucv.application.cli.builder;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import com.ace.ucv.model.cli.Argument;
import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
import com.ace.ucv.model.xml.nota.NotaStudType;
import com.ace.ucv.model.xml.nota.NoteType;
import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.adapter.DisciplineMapper;
import com.ace.ucv.service.adapter.GradeMapper;
import com.ace.ucv.service.adapter.StudentMapper;
import com.ace.ucv.service.adapter.iface.IDisciplineMapper;
import com.ace.ucv.service.adapter.iface.IGradeMapper;
import com.ace.ucv.service.adapter.iface.IStudentMapper;
import com.ace.ucv.service.exception.ConfigurationLoaderException;
import com.ace.ucv.service.output.CatalogGeneration;
import com.ace.ucv.service.parser.DisciplineParser;
import com.ace.ucv.service.parser.GradeParser;
import com.ace.ucv.service.parser.StudentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Draghici on 12/2/2023
 * Name of project: StudentManagement
 */

/*
 * CLIBuilder class is responsible for building the application components
 * and executing the parsing of CLI arguments.
 */
public class CLIBuilder {

    private static final Logger logger = LogManager.getLogger(CLIBuilder.class);

    // Mapper objects for adapting XML objects to intermediary objects
    private final IStudentMapper studentMapper;
    private final IDisciplineMapper disciplineMapper;
    private final IGradeMapper gradeMapper;

    /**
     * Constructor for CLIBuilder. Initializes mapper objects.
     */
    public CLIBuilder() {
        this.studentMapper = new StudentMapper();
        this.disciplineMapper = new DisciplineMapper();
        this.gradeMapper = new GradeMapper();
    }

    /**
     * Executes the parsing of CLI arguments and builds the application components.
     *
     * @param argument - Argument object containing CLI arguments
     */
    public void executeParsing(Argument argument) {

        try {
            ArgumentValidator argumentValidator = new ArgumentValidator();
            argumentValidator.validateArguments(argument);

            List<Student> studentList = loadStudentInformation(argument);
            List<Discipline> disciplineList = loadDisciplineInformation(argument);
            List<Grade> gradeList = loadGradeInformation(argument);

            Catalog catalog = new Catalog();
            catalog.setDisciplines(disciplineList);
            catalog.setStudents(studentList);
            catalog.setGrades(gradeList);

            CatalogGeneration generation = new CatalogGeneration();
            generation.generateXMLCatalog(catalog, argument.getOutputCatalogPath());

        } catch (Exception exception) {
            logger.warn("Failed to execute CLI parsing due to: " + exception.getMessage());
        }
    }


    /**
     * Loads grade information from the provided XML file path.
     *
     * @param argument - Argument object containing file paths
     * @return List of Grade objects
     * @throws ConfigurationLoaderException if loading configuration fails
     */
    private List<Grade> loadGradeInformation(Argument argument) throws ConfigurationLoaderException {
        GradeParser gradeParser = new GradeParser();
        NoteType noteType = gradeParser.loadConfiguration(new File(argument.getGradeFilePath()));

        List<Grade> gradeList = new ArrayList<>();
        Grade grade;

        for (NotaStudType notaStudType : noteType.getNotaStud()) {
            grade = gradeMapper.adaptXmlObjectToGradeIntermediaryObject(notaStudType);
            gradeList.add(grade);
        }

        logger.info("Grades information loaded successfully.");

        return gradeList;

    }

    /**
     * Loads discipline information from the provided XML file path.
     *
     * @param argument - Argument object containing file paths
     * @return List of Discipline objects
     * @throws ConfigurationLoaderException if loading configuration fails
     */
    private List<Discipline> loadDisciplineInformation(Argument argument) throws ConfigurationLoaderException {

        DisciplineParser disciplineParser = new DisciplineParser();
        MateriiType materiiType = disciplineParser.loadConfiguration(new File(argument.getDisciplineFilePath()));

        List<Discipline> disciplineList = new ArrayList<>();
        Discipline discipline;

        for (MateriaType materiaType : materiiType.getMateria()) {
            discipline = disciplineMapper.adaptXmlObjectToDisciplineIntermediaryObject(materiaType);
            disciplineList.add(discipline);
        }

        logger.info("Disciplines information loaded successfully.");

        return disciplineList;
    }

    /**
     * Loads student information from the provided XML file path.
     *
     * @param argument - Argument object containing file paths
     * @return List of Student objects
     * @throws ConfigurationLoaderException if loading configuration fails
     */
    private List<Student> loadStudentInformation(Argument argument) throws ConfigurationLoaderException {
        StudentParser studentParser = new StudentParser();
        StudentiType studentData = studentParser.loadConfiguration(new File(argument.getStudentFilePath()));

        List<Student> studentList = new ArrayList<>();
        Student student;

        for (StudentType studentType : studentData.getStudent()) {
            student = studentMapper.adaptXmlObjectToStudentIntermediaryObject(studentType);
            studentList.add(student);
        }

        logger.info("Students information loaded successfully.");

        return studentList;
    }
}