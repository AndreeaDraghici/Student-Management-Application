package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.exception.ConfigurationLoaderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 10/27/2023
 * Name of project: StudentManagement
 */
class StudentParserTest {

    public static final String PATHNAME = "src/main/resources/input_files/Student.xml";

    @Test
    void loadConfigurationTest() {
        StudentParser xmlParser = new StudentParser();

        try {
            StudentiType type = xmlParser.loadConfiguration(new File(PATHNAME));
            List<StudentType> typeStudent = type.getStudent();

            Assertions.assertEquals(5, typeStudent.size());
            Assertions.assertEquals("1", typeStudent.get(0).getId().toString());
            Assertions.assertEquals("Vaneet", typeStudent.get(1).getNume());
            Assertions.assertEquals("singn", typeStudent.get(2).getPrenume());
            Assertions.assertEquals("07355555551", typeStudent.get(3).getTelefon());
            Assertions.assertEquals("Female", typeStudent.get(4).getSex());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void test_throw_exception_when_xml_file_is_null() {
        StudentParser xmlParser = new StudentParser();

        assertThrows(IllegalArgumentException.class, () -> xmlParser.loadConfiguration(null));
    }

    @Test
    void test_throw_exception_when_xml_file_path_is_incorrect() {
        StudentParser xmlParser = new StudentParser();

        assertThrows(ConfigurationLoaderException.class, () -> xmlParser.loadConfiguration(new File("invalid/path/to/file.xml")));
    }
}