package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

/*
 * Custom exception class for handling exceptions related to catalog generation in the application.
 * Extends the RuntimeException class for unchecked exception handling.
 *
 * This exception is typically thrown when there are issues generating a catalog, such as formatting errors or missing data.
 */
public class CatalogGenerationException extends RuntimeException {

    /**
     * Constructs a new CatalogGenerationException with the specified detail message.
     *
     * @param message The detail message providing information about the exception.
     */
    public CatalogGenerationException(String message) {
        super(message);
    }

    /**
     * Constructs a new CatalogGenerationException with the specified detail message and cause.
     *
     * @param message The detail message providing information about the exception.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public CatalogGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
