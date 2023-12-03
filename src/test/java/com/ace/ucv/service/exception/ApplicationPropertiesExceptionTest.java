package com.ace.ucv.service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
class ApplicationPropertiesExceptionTest {

    @Test
    void testApplicationPropertiesExceptionConstructorWithMessage() {
        // Arrange
        String message = "Test message";

        // Act
        ApplicationPropertiesException exception = new ApplicationPropertiesException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testApplicationPropertiesExceptionConstructorWithMessageAndCause() {
        // Arrange
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");

        // Act
        ApplicationPropertiesException exception = new ApplicationPropertiesException(message, cause);

        // Assert
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}