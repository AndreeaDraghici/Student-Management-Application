package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class ConfigurationLoadException extends Exception {

    public ConfigurationLoadException(String message) {
        super(message);
    }

    public ConfigurationLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
