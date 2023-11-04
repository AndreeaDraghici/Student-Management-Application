package com.ace.ucv.service.output;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import com.ace.ucv.service.exception.CatalogGenerationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import java.util.List;


/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class CatalogProcessingManager {

    private static final Logger logger = LogManager.getLogger(CatalogProcessingManager.class);


    /**
     * Generates a catalog XML file based on the provided lists of students, disciplines, and grades.
     *
     * @param students    List of students.
     * @param disciplines List of disciplines.
     * @param grades      List of grades.
     * @param filePath    The path where the catalog XML file will be generated.
     */
    public void generateCatalogXML(List<Student> students, List<Discipline> disciplines, List<Grade> grades, String filePath) {

        inputChecks(students, disciplines, grades, filePath);

        ProcessingManager manager = new ProcessingManager();
        try {
            Document document = manager.createXmlDocument(students, disciplines, grades);
            manager.saveXmlDocument(document, filePath);
            logger.info("Catalog file was created!");
        } catch (Exception e) {
            throw new CatalogGenerationException("Failed to generate the output file", e);
        }
    }

    /**
     * Validates input parameters for generating the catalog XML file.
     *
     * @param students    List of students.
     * @param disciplines List of disciplines.
     * @param grades      List of grades.
     * @param filePath    The path where the catalog XML file will be generated.
     * @throws RuntimeException if any of the input parameters are invalid or missing.
     */
    private void inputChecks(List<Student> students, List<Discipline> disciplines, List<Grade> grades, String filePath) {
        if (students == null) {
            throw new CatalogGenerationException("Catalog must have the associated students!");
        }

        if (disciplines == null) {
            throw new CatalogGenerationException("Catalog must have the associated disciplines!");
        }

        if (grades == null) {
            throw new CatalogGenerationException("Discipline must have the associated grades!");
        }

        if (filePath.isEmpty()) {
            throw new CatalogGenerationException("Output file need to be exist to generate the catalog file!");
        }
    }
}