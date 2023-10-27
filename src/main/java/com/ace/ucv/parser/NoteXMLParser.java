package com.ace.ucv.parser;

import com.ace.ucv.model.xml.nota.NoteType;
import javax.xml.bind.*;
import java.io.File;
import java.nio.file.Files;


/**
 * Created by Andreea Draghici on 10/24/2023
 * Name of project: StudentManagement
 */

// Parser specific to the Nota entity
public class NoteXMLParser{

    /**
     * deserialization from xml file into object
     */
    public NoteType loadConfiguration(File file) throws RuntimeException, Exception {

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