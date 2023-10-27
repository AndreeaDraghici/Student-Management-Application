package com.ace.ucv.parser;

import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 10/27/2023
 * Name of project: StudentManagement
 */
class DisciplineXMLParserTest {

    @Test
    void loadConfigurationTest() {

        DisciplineXMLParser xmlParser = new DisciplineXMLParser();

        try {
            MateriiType type = xmlParser.loadConfiguration(new File("src/main/resources/view/Materie.xml"));
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
}