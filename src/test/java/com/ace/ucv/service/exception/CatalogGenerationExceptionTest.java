package com.ace.ucv.service.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class CatalogGenerationExceptionTest {

    @Test
    @DisplayName("Test CatalogGenerationException constructor with message")
    void testCatalogGenerationExceptionWithMessage() {
        // Arrange
        String expectedMessage = "Test message";

        // Act
        Exception exception = assertThrows(CatalogGenerationException.class, () -> {
            throw new CatalogGenerationException(expectedMessage);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test CatalogGenerationException constructor with message and cause")
    void testCatalogGenerationExceptionWithMessageAndCause() {
        // Arrange
        String expectedMessage = "Test message";
        Throwable expectedCause = new RuntimeException("Test cause");

        // Act
        Exception exception = assertThrows(CatalogGenerationException.class, () -> {
            throw new CatalogGenerationException(expectedMessage, expectedCause);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(expectedCause, exception.getCause());
    }

}