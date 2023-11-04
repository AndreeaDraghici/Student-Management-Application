package com.ace.ucv.service.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class ConfigurationLoadExceptionTest {
    @Test
    void test_setMessageCorrectly() {
        String message = "Test message";
        ConfigurationLoadException exception = new ConfigurationLoadException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void test_nullCause() {
        String message = "Test message";
        Throwable cause = null;
        ConfigurationLoadException exception = new ConfigurationLoadException(message, cause);
        assertNull(exception.getCause());
    }
}