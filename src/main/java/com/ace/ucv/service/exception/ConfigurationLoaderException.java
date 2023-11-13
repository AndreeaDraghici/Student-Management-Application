package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

/*
 * Custom exception class for handling exceptions related to configuration loading in the application.
 * Extends the Exception class for checked exception handling.
 *
 * This exception is typically thrown when there are issues loading configuration data.
 */
public class ConfigurationLoaderException extends Exception {
    /**
     * Constructs a new ConfigurationLoaderException with the specified detail message.
     *
     * @param message The detail message providing information about the exception.
     */
    public ConfigurationLoaderException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConfigurationLoaderException with the specified detail message and cause.
     *
     * @param message The detail message providing information about the exception.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public ConfigurationLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
