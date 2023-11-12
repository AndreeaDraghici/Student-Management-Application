package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
public class ApplicationPropertiesException extends RuntimeException {

    public ApplicationPropertiesException(String message) {
        super(message);
    }

    public ApplicationPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
