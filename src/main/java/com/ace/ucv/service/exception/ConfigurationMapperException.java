package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

/*
 * Custom exception class for handling exceptions related to configuration mapping in the application.
 * Extends the RuntimeException class for unchecked exception handling.
 */
public class ConfigurationMapperException extends RuntimeException {

    /**
     * Constructs a new ConfigurationMapperException with the specified detail message.
     *
     * @param message The detail message providing information about the exception.
     */
    public ConfigurationMapperException(String message) {
        super(message);
    }


    /**
     * Constructs a new ConfigurationMapperException with the specified detail message and cause.
     *
     * @param message The detail message providing information about the exception.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public ConfigurationMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}