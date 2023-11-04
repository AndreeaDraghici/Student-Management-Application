package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class ConfigurationMapperException extends RuntimeException {

    public ConfigurationMapperException(String message) {
        super(message);
    }

    public ConfigurationMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
