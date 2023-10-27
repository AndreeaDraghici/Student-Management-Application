package com.ace.ucv.parser;

import com.ace.ucv.model.xml.nota.NoteType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 10/27/2023
 * Name of project: StudentManagement
 */
class NoteXMLParserTest {

    @Test
    void loadConfigurationTest() {

        NoteXMLParser xmlParser = new NoteXMLParser();

        try {
            NoteType type = xmlParser.loadConfiguration(new File("src/main/resources/view/Nota.xml"));
            Assertions.assertEquals(8, type.getNotaStud().size());
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }
}