package com.ace.ucv.service.parser.iface;

import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.exception.ConfigurationLoaderException;

import java.io.File;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
public interface IStudentParser {

    /**
     * Checks the input file for validity and throws a ConfigurationLoaderException if the check fails.
     *
     * @param file The input file to be checked.
     * @throws ConfigurationLoaderException If there is an issue with the configuration loading process.
     */
    void inputCheck(File file) throws ConfigurationLoaderException;

    /**
     * Loads the configuration from the specified input file.
     *
     * @param file The input file from which to load the configuration.
     * @return The loaded configuration as an object of type StudentiType.
     * @throws ConfigurationLoaderException If there is an issue with the configuration loading process.
     */
    StudentiType loadConfiguration(File file) throws ConfigurationLoaderException;
}
