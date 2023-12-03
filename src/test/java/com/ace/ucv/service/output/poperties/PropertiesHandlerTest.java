package com.ace.ucv.service.output.poperties;

import com.ace.ucv.service.exception.ApplicationPropertiesException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
class PropertiesHandlerTest {


    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
    @Test
    void testSavePropertiesWhenModelAndFileAreValidThenPropertiesAreSaved() {
        // Arrange
        PropertiesModel model = new PropertiesModel("studentPath", "disciplinePath", "gradePath");
        File file = new File("./src/test/resources/out/test.properties");

        PropertiesHandler handler = new PropertiesHandler();

        // Act
        handler.saveProperties(model, file);

        // Assert
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // Clean up
        file.delete();
    }

    @Test
    void testSavePropertiesWhenModelIsNullThenExceptionIsThrown() {
        // Arrange
        File file = new File("test.properties");

        PropertiesHandler handler = new PropertiesHandler();

        // Act and Assert
        assertThrows(ApplicationPropertiesException.class, () -> handler.saveProperties(null, file));
    }

    @Test
    void testSavePropertiesWhenFileIsNullThenExceptionIsThrown() {
        // Arrange
        PropertiesModel model = new PropertiesModel("studentPath", "disciplinePath", "gradePath");

        PropertiesHandler handler = new PropertiesHandler();

        // Act and Assert
        assertThrows(ApplicationPropertiesException.class, () -> handler.saveProperties(model, null));
    }

    @Test
    void testLoadPropertiesWhenFileNotFoundThenExceptionIsThrown() {
        // Arrange
        File file = new File("nonexistent.properties");

        PropertiesHandler handler = new PropertiesHandler();

        // Act and Assert
        assertThrows(ApplicationPropertiesException.class, () -> handler.loadProperties(file));
    }

    @Test
    void testLoadPropertiesWhenIOErrorThenExceptionIsThrown() throws IOException {
        // Arrange
        File file = new File("src/test/resources/out/gh.properties");

        PropertiesHandler handler = new PropertiesHandler();

        // Act and Assert
        assertThrows(ApplicationPropertiesException.class, () -> handler.loadProperties(file));

        // Clean up
        file.setReadable(true);
    }
}