package com.ace.ucv.service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class ConfigurationLoaderExceptionTest {
    @Test
    void test_setMessageCorrectly() {
        String message = "Test message";
        ConfigurationLoaderException exception = new ConfigurationLoaderException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void test_nullCause() {
        String message = "Test message";
        Throwable cause = null;
        ConfigurationLoaderException exception = new ConfigurationLoaderException(message, cause);
        assertNull(exception.getCause());
    }
}