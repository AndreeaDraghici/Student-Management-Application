package com.ace.ucv.service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class ConfigurationMapperExceptionTest {
    @Test
    void testConfigurationMapperExceptionWithMessage() {
        // Arrange
        String expectedMessage = "Test message";

        // Act
        ConfigurationMapperException exception = new ConfigurationMapperException(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testConfigurationMapperExceptionWithMessageAndCause() {
        // Arrange
        String expectedMessage = "Test message";
        Throwable expectedCause = new RuntimeException("Test cause");

        // Act
        ConfigurationMapperException exception = new ConfigurationMapperException(expectedMessage, expectedCause);

        // Assert
        assertAll(
                () -> assertEquals(expectedMessage, exception.getMessage()),
                () -> assertEquals(expectedCause, exception.getCause())
        );
    }
}