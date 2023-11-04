package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.materie.MateriiType;
import com.ace.ucv.service.exception.ConfigurationLoadException;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Andreea Draghici on 10/24/2023
 * Name of project: StudentManagement
 */

public class DisciplineParser {

    /**
     * Deserializes an XML file into a MateriiType object using JAXB.
     *
     * @param file The XML configuration file to be deserialized.
     * @return The MateriiType object representing the deserialized data.
     * @throws ConfigurationLoadException If any exception occurs during the deserialization process.
     */
    public MateriiType loadConfiguration(File file) throws ConfigurationLoadException {
        if (file == null) {
            throw new IllegalArgumentException("XML configuration file is null.");
        }

        if (!file.exists()) {
            throw new ConfigurationLoadException(file.getPath() + " could not be found!");
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.ace.ucv.model.xml.materie");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (MateriiType) JAXBIntrospector.getValue(unmarshaller.unmarshal(Files.newInputStream(file.toPath())));
        } catch (JAXBException | IOException e) {
            throw new ConfigurationLoadException("Failed to load the discipline configuration from XML file.", e);
        }
    }
}