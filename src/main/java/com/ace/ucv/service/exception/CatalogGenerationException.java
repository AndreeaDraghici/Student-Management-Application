package com.ace.ucv.service.exception;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class CatalogGenerationException extends RuntimeException {

    public CatalogGenerationException(String message) {
        super(message);
    }

    public CatalogGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
