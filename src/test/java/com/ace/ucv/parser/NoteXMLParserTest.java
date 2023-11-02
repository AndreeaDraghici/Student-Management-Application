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
    public void test_load_valid_xml_configuration_file() {
        NoteXMLParser xmlParser = new NoteXMLParser();

        try {
            NoteType type = xmlParser.loadConfiguration(new File("src/main/resources/view/Nota.xml"));
            Assertions.assertEquals(8, type.getNotaStud().size());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void test_throw_exception_if_xml_configuration_file_is_null() {
        NoteXMLParser xmlParser = new NoteXMLParser();

        assertThrows(IllegalArgumentException.class, () -> {
            xmlParser.loadConfiguration(null);
        });
    }

    @Test
    public void test_throw_exception_if_xml_configuration_file_not_found() {
        NoteXMLParser xmlParser = new NoteXMLParser();

        assertThrows(RuntimeException.class, () -> {
            xmlParser.loadConfiguration(new File("nonexistent.xml"));
        });
    }

    @Test
    public void test_return_NoteType_with_list_of_NotaStudType_objects() {
        NoteXMLParser xmlParser = new NoteXMLParser();

        try {
            NoteType type = xmlParser.loadConfiguration(new File("src/main/resources/view/Nota.xml"));
            assertNotNull(type.getNotaStud());
            assertFalse(type.getNotaStud().isEmpty());
        } catch (Exception e) {
            fail(e);
        }
    }
}