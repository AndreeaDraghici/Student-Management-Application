package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.nota.NoteType;
import com.ace.ucv.service.exception.ConfigurationLoadException;
import com.ace.ucv.service.exception.ConfigurationMapperException;
import com.ace.ucv.service.parser.GradeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 10/27/2023
 * Name of project: StudentManagement
 */
class GradeParserTest {

    public static final String PATHNAME = "src/main/resources/input_files/Nota.xml";

    @Test
    void test_load_valid_xml_configuration_file() {
        GradeParser xmlParser = new GradeParser();

        try {
            NoteType type = xmlParser.loadConfiguration(new File(PATHNAME));
            Assertions.assertEquals(8, type.getNotaStud().size());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void test_throw_exception_if_xml_configuration_file_is_null() {
        GradeParser xmlParser = new GradeParser();
        assertThrows(IllegalArgumentException.class, () -> xmlParser.loadConfiguration(null));
    }

    @Test
    void test_throw_exception_if_xml_configuration_file_not_found() {
        GradeParser xmlParser = new GradeParser();
        assertThrows(ConfigurationLoadException.class, () -> xmlParser.loadConfiguration(new File("nonexistent.xml")));
    }

    @Test
    void test_return_NoteType_with_list_of_NotaStudType_objects() {
        GradeParser xmlParser = new GradeParser();

        try {
            NoteType type = xmlParser.loadConfiguration(new File(PATHNAME));
            assertNotNull(type.getNotaStud());
            assertFalse(type.getNotaStud().isEmpty());
        } catch (Exception e) {
            fail(e);
        }
    }
}