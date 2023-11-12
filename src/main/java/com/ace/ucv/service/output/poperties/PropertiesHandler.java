package com.ace.ucv.service.output.poperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static com.ace.ucv.utils.ApplicationPropertiesConstants.*;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */

/*
* Utility class for handling application properties, providing methods to load and save properties.
 */
public class PropertiesHandler {

    // Configuration properties instance
    private final Properties instance = new Properties();

    /**
     * Loads properties from the specified file into a PropertiesModel.
     *
     * @param file The file from which properties are to be loaded.
     * @return A PropertiesModel containing the loaded properties.
     * @throws RuntimeException If an error occurs during loading.
     */
    public PropertiesModel loadProperties(File file) {
        PropertiesModel model = new PropertiesModel();

        try (InputStream stream = Files.newInputStream(file.toPath())) {
            instance.load(stream);

            model.setStudentPath(instance.getProperty(STUDENT_PATH));
            model.setDisciplinePath(instance.getProperty(DISCIPLINE_PATH));
            model.setGradePath(instance.getProperty(GRADE_PATH));

            return model;
        } catch (IOException ioException) {
            throw new RuntimeException(String.format("Failed to load the properties due to: %s", ioException.getMessage()));
        }
    }


    /**
     * Saves properties from the provided PropertiesModel to the specified file.
     *
     * @param model The PropertiesModel containing properties to be saved.
     * @param file  The file to which properties are to be saved.
     * @throws RuntimeException If an error occurs during saving.
     */
    public void saveProperties(PropertiesModel model, File file) {
        checkInputs(model, file);

        try (FileWriter writer = new FileWriter(file)) {
            setConfigurationProperties(model);
            instance.store(writer, null);
        } catch (IOException ioException) {
            throw new RuntimeException(String.format("Could not save properties file to the system due to: %s", ioException.getMessage()));
        }
    }


    /**
     * Checks if the provided model and file are not null.
     *
     * @param model The PropertiesModel to be checked.
     * @param file  The File to be checked.
     * @throws RuntimeException If either the model or the file is null.
     */
    private static void checkInputs(PropertiesModel model, File file) {
        if (model == null) {
            throw new RuntimeException("Properties handler module cannot be null!");
        }

        if (file == null) {
            throw new RuntimeException("Properties handler file cannot be null!");
        }
    }


    /**
     * Sets the configuration properties based on the provided PropertiesModel.
     *
     * @param model The PropertiesModel containing properties to be set.
     */
    private void setConfigurationProperties(PropertiesModel model) {
        if (model.getStudentPath() == null) {
            instance.setProperty(STUDENT_PATH, "");
        } else {
            instance.setProperty(STUDENT_PATH, model.getStudentPath());
        }

        if (model.getDisciplinePath() == null) {
            instance.setProperty(DISCIPLINE_PATH, "");
        } else {
            instance.setProperty(DISCIPLINE_PATH, model.getDisciplinePath());
        }

        if (model.getGradePath() == null) {
            instance.setProperty(GRADE_PATH, "");
        } else {
            instance.setProperty(GRADE_PATH, model.getGradePath());
        }
    }
}