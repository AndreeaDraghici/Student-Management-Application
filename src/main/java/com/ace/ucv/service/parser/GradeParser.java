package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.nota.NoteType;
import javax.xml.bind.*;
import java.io.File;
import java.nio.file.Files;


/**
 * Created by Andreea Draghici on 10/24/2023
 * Name of project: StudentManagement
 */

public class GradeParser {

    /**
     * Deserializes an XML file into a NoteType object using JAXB.
     *
     * @param file The XML configuration file to be deserialized.
     * @return The NoteType object representing the deserialized data.
     * @throws Exception If any exception occurs during the deserialization process.
     */
    public NoteType loadConfiguration(File file) throws Exception {

        if (file == null) {
            throw new IllegalArgumentException("XML configuration file is null.");
        }

        if (!file.exists()) {
            throw new RuntimeException(file.getPath() + " could not be found!");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance("com.ace.ucv.model.xml.nota");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (NoteType) JAXBIntrospector.getValue(unmarshaller.unmarshal(Files.newInputStream(file.toPath())));
    }
}