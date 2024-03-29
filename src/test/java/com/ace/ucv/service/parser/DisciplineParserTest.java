package com.ace.ucv.service.parser;

import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
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
class DisciplineParserTest {

    public static final String PATHNAME = "src/main/resources/input_files/Materie.xml";

    @Test
    void loadConfigurationTest() {

        DisciplineParser xmlParser = new DisciplineParser();

        try {
            MateriiType type = xmlParser.loadConfiguration(new File(PATHNAME));
            List<MateriaType> typeMateria = type.getMateria();

            Assertions.assertEquals(4, typeMateria.size());
            Assertions.assertEquals("1", typeMateria.get(0).getId());
            Assertions.assertEquals("I", typeMateria.get(1).getAn());
            Assertions.assertEquals("Matematica", typeMateria.get(2).getDenumire());
            Assertions.assertEquals("Profesor4", typeMateria.get(3).getProfesor());
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }

    @Test
    void test_throw_exception_when_file_argument_is_null() {
        DisciplineParser xmlParser = new DisciplineParser();

        assertThrows(IllegalArgumentException.class, () -> xmlParser.loadConfiguration(null));
    }

    @Test
    void test_throw_exception_when_file_argument_does_not_exist() {
        DisciplineParser xmlParser = new DisciplineParser();

        assertThrows(ConfigurationLoaderException.class, () -> xmlParser.loadConfiguration(new File("nonexistent.xml")));
    }

    @Test
    void test_single_materia_element() {
        DisciplineParser xmlParser = new DisciplineParser();

        try {
            MateriiType type = xmlParser.loadConfiguration(new File(PATHNAME));
            List<MateriaType> typeMateria = type.getMateria();

            Assertions.assertEquals(4, typeMateria.size());
            Assertions.assertEquals("1", typeMateria.get(0).getId());
            Assertions.assertEquals("I", typeMateria.get(0).getAn());
            Assertions.assertEquals("Romana", typeMateria.get(0).getDenumire());
            Assertions.assertEquals("Profesor1", typeMateria.get(0).getProfesor());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}