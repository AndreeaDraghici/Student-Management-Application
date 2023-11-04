package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class ConfigurationLoaderException extends Exception {

    public ConfigurationLoaderException(String message) {
        super(message);
    }

    public ConfigurationLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
