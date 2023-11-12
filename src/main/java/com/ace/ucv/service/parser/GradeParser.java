package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.nota.NoteType;
import com.ace.ucv.service.exception.ConfigurationLoaderException;
import com.ace.ucv.service.parser.iface.IConfigurationLoader;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * Created by Andreea Draghici on 10/24/2023
 * Name of project: StudentManagement
 */

// Implement the methods from the interface
public class GradeParser implements IConfigurationLoader<NoteType> {

    /**
     * Deserializes an XML file into a NoteType object using JAXB.
     *
     * @param file The XML configuration file to be deserialized.
     * @return The NoteType object representing the deserialized data.
     * @throws ConfigurationLoaderException If any exception occurs during the deserialization process.
     */
    @Override
    public NoteType loadConfiguration(File file) throws ConfigurationLoaderException {
        inputCheck(file);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.ace.ucv.model.xml.nota");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (NoteType) JAXBIntrospector.getValue(unmarshaller.unmarshal(Files.newInputStream(file.toPath())));
        } catch (JAXBException | IOException e) {
            throw new ConfigurationLoaderException(String.format("Failed to load the grade configuration from XML file:  %s", file.getPath()), e);
        }
    }

    /**
     * Performs input checks for the specified file.
     *
     * @param file The XML configuration file to be checked.
     * @throws ConfigurationLoaderException If any input parameters are invalid or missing.
     */
    @Override
    public void inputCheck(File file) throws ConfigurationLoaderException {
        if (file == null) {
            throw new IllegalArgumentException("XML configuration file is null.");
        }

        if (!file.exists()) {
            throw new ConfigurationLoaderException(String.format("%s could not be found!", file.getPath()));
        }
    }
}
