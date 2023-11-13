package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */

/*
 * Custom exception class for handling exceptions related to application properties in the application.
 * Extends the RuntimeException class for unchecked exception handling.
 *
 * This exception is typically thrown when there are issues with loading or accessing application properties,
 * such as missing configuration files or invalid property values.
 */
public class ApplicationPropertiesException extends RuntimeException {

    /**
     * Constructs a new ApplicationPropertiesException with the specified detail message.
     *
     * @param message The detail message providing information about the exception.
     */
    public ApplicationPropertiesException(String message) {
        super(message);
    }


    /**
     * Constructs a new ApplicationPropertiesException with the specified detail message and cause.
     *
     * @param message The detail message providing information about the exception.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public ApplicationPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }
}