package com.ace.ucv.service.output.poperties.iface;

import com.ace.ucv.service.exception.ApplicationPropertiesException;
import com.ace.ucv.service.output.poperties.PropertiesModel;

import java.io.File;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */

/*
 * Interface for handling properties, providing methods to load and save properties.
 * Implementations of this interface are responsible for interacting with property files and converting them to/from a PropertiesModel.
 */
public interface IProperties {

    /**
     * Loads properties from the specified file into a PropertiesModel.
     *
     * @param file The file from which properties are to be loaded.
     * @return A PropertiesModel containing the loaded properties.
     * @throws ApplicationPropertiesException If an error occurs during loading.
     */
    PropertiesModel loadProperties(File file) throws ApplicationPropertiesException;

    /**
     * Saves properties from the provided PropertiesModel to the specified file.
     *
     * @param model The PropertiesModel containing properties to be saved.
     * @param file  The file to which properties are to be saved.
     * @throws ApplicationPropertiesException If an error occurs during saving.
     */
    void saveProperties(PropertiesModel model, File file) throws ApplicationPropertiesException;
}