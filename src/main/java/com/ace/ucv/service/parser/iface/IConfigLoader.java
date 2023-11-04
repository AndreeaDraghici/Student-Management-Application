package com.ace.ucv.service.parser.iface;

import com.ace.ucv.service.exception.ConfigurationLoaderException;

import java.io.File;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

/**
 * Generic interface for configuration loaders.
 *
 * @param <T> The type of configuration object to be loaded.
 */
public interface IConfigLoader<T> {

    /**
     * Loads configuration from the specified file.
     *
     * @param file The XML configuration file to be loaded.
     * @return The configuration object representing the deserialized data.
     * @throws ConfigurationLoaderException If any exception occurs during the deserialization process.
     */
    T loadConfiguration(File file) throws ConfigurationLoaderException;


    /**
     * Performs input checks for the specified file.
     *
     * @param file The XML configuration file to be checked.
     * @throws ConfigurationLoaderException If any input parameters are invalid or missing.
     */
    void inputCheck(File file) throws ConfigurationLoaderException;
}
