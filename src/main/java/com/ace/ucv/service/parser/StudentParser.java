package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.exception.ConfigurationLoadException;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Andreea Draghici on 10/24/2023
 * Name of project: StudentManagement
 */

public class StudentParser {

    /**
     * Deserializes an XML file into a StudentiType object using JAXB.
     *
     * @param file The XML configuration file to be deserialized.
     * @return The StudentiType object representing the deserialized data.
     * @throws ConfigurationLoadException If any exception occurs during the deserialization process.
     */
    public StudentiType loadConfiguration(File file) throws ConfigurationLoadException {
        if (file == null) {
            throw new IllegalArgumentException("XML configuration file is null.");
        }

        if (!file.exists()) {
            throw new ConfigurationLoadException(file.getPath() + " could not be found!");
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.ace.ucv.model.xml.student");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (StudentiType) JAXBIntrospector.getValue(unmarshaller.unmarshal(Files.newInputStream(file.toPath())));
        } catch (JAXBException | IOException e) {
            throw new ConfigurationLoadException("Failed to load the student configuration from XML file.", e);
        }
    }
}