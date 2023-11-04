package com.ace.ucv.service.output;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.service.exception.CatalogGenerationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;


/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class CatalogProcessingManager {

    private static final Logger logger = LogManager.getLogger(CatalogProcessingManager.class);

    /**
     * Generates a catalog XML file based on the provided catalog, list of grades, and file path.
     *
     * @param catalog  The catalog containing the list of students , grades and disciplines.
     * @param filePath The path where the catalog XML file will be generated.
     * @throws CatalogGenerationException if the generation process fails.
     */
    public void generateCatalogXML(Catalog catalog, String filePath) {

        inputChecks(catalog, filePath);
        ProcessingManager manager = new ProcessingManager();

        try {
            Document document = manager.createXmlDocument(catalog);
            manager.saveXmlDocument(document, filePath);
            logger.info("Catalog file was created!");
        } catch (Exception e) {
            throw new CatalogGenerationException("Failed to generate the output file", e);
        }
    }

    /**
     * Validates input parameters for generating the catalog XML file.
     *
     * @param catalog  The catalog containing the list of students, grades and disciplines.
     * @param filePath The path where the catalog XML file will be generated.
     * @throws CatalogGenerationException if any of the input parameters are invalid or missing.
     */
    private void inputChecks(Catalog catalog, String filePath) {
        if (catalog.getStudents().isEmpty()) {
            throw new CatalogGenerationException("Catalog must have the associated students!");
        }

        if (catalog.getDisciplines().isEmpty()) {
            throw new CatalogGenerationException("Catalog must have the associated disciplines!");
        }

        if (catalog.getGrades().isEmpty()) {
            throw new CatalogGenerationException("Discipline must have the associated grades!");
        }

        if (filePath.isEmpty()) {
            throw new CatalogGenerationException("Output file need to be exist to generate the catalog file!");
        }
    }

}