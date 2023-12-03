package com.ace.ucv.service.output.poperties;

import com.ace.ucv.service.exception.ApplicationPropertiesException;
import com.ace.ucv.service.output.poperties.iface.IProperties;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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
public class PropertiesHandler implements IProperties {

    // Configuration properties instance
    private final Properties instance = new Properties();

    /**
     * Loads properties from the specified file into a PropertiesModel.
     *
     * @param file The file from which properties are to be loaded.
     * @return A PropertiesModel containing the loaded properties.
     * @throws ApplicationPropertiesException If an error occurs during loading.
     */
    @Override
    public PropertiesModel loadProperties(File file) throws ApplicationPropertiesException {
        PropertiesModel model = new PropertiesModel();

        try (InputStream stream = Files.newInputStream(file.toPath())) {
            instance.load(stream);
            setModelProperties(model);
            return model;
        } catch (IOException ioException) {
            throw new ApplicationPropertiesException("Failed to load the properties.", ioException);
        }
    }


    /**
     * Saves properties from the provided PropertiesModel to the specified file.
     *
     * @param model The PropertiesModel containing properties to be saved.
     * @param file  The file to which properties are to be saved.
     * @throws ApplicationPropertiesException If an error occurs during saving.
     */
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    @Override
    public void saveProperties(PropertiesModel model, File file) throws ApplicationPropertiesException {
        checkInputs(model, file);

        try (FileWriter writer = new FileWriter(file)) {
            setConfigurationProperties(model);
            instance.store(writer, null);
        } catch (IOException ioException) {
            throw new ApplicationPropertiesException("Could not save properties file to the system.", ioException);
        }
    }


    /**
     * Checks if the provided model and file are not null.
     *
     * @param model The PropertiesModel to be checked.
     * @param file  The File to be checked.
     * @throws ApplicationPropertiesException If either the model or the file is null.
     */
    private static void checkInputs(PropertiesModel model, File file) {
        if (model == null) {
            throw new ApplicationPropertiesException("Properties handler module cannot be null!");
        }

        if (file == null) {
            throw new ApplicationPropertiesException("Properties handler file cannot be null!");
        }
    }


    /**
     * Sets the configuration properties based on the provided PropertiesModel.
     *
     * @param model The PropertiesModel containing properties to be set.
     */
    private void setConfigurationProperties(PropertiesModel model) {
        setPropertyIfNotNull(STUDENT_PATH, model.getStudentPath());
        setPropertyIfNotNull(DISCIPLINE_PATH, model.getDisciplinePath());
        setPropertyIfNotNull(GRADE_PATH, model.getGradePath());
    }


    /**
     * Sets properties in the provided PropertiesModel.
     *
     * @param model The PropertiesModel to be set.
     */
    private void setModelProperties(PropertiesModel model) {
        model.setStudentPath(instance.getProperty(STUDENT_PATH));
        model.setDisciplinePath(instance.getProperty(DISCIPLINE_PATH));
        model.setGradePath(instance.getProperty(GRADE_PATH));
    }

    /**
     * Sets a property in the instance if the provided value is not null.
     *
     * @param key   The key of the property.
     * @param value The value to be set.
     */
    private void setPropertyIfNotNull(String key, String value) {
        if (value == null) {
            instance.setProperty(key, "");
        } else {
            instance.setProperty(key, value);
        }
    }
}