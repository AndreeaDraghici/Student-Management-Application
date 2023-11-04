package com.ace.ucv.service.output;


import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import com.ace.ucv.service.exception.CatalogGenerationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogGenerationTest {

    private CatalogGeneration catalogGeneration;
    private Student student;
    private Discipline discipline;
    private List<Grade> grades;
    private String filePath;
    private Catalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new Catalog();
        catalogGeneration = new CatalogGeneration();
        student = new Student();
        student.setId(1);
        student.setName("John");
        student.setSurname("Doe");
        student.setPhone("+40721111111");
        student.setGenre("Male");
        catalog.addStudent(student);

        discipline = new Discipline();
        discipline.setId(1);
        discipline.setName("Math");
        discipline.setTeacher("Mr. Smith");
        discipline.setYear("2023");
        discipline.setSemester(1);
        catalog.addDiscipline(discipline);

        grades = Arrays.asList(new Grade(10, 1, 1));
        catalog.setGrades(grades);
        filePath = "src/test/resources/out/catalog.xml";
    }

    @Test
    void testGenerateCatalogXMLWhenValidDataThenCatalogFileGenerated() {
        catalogGeneration.generateXMLCatalog(catalog, filePath);
        File file = new File(filePath);
        assertTrue(file.exists());
    }

    @Test
    void testGenerateCatalogXMLWhenGradesNullThenRuntimeException() {
        catalog.setGrades(null);
        Exception exception = assertThrows(CatalogGenerationException.class, () -> catalogGeneration.generateXMLCatalog(catalog, filePath));
        assertEquals("Discipline must have the associated grades!", exception.getMessage());
    }

    @Test
    void testGenerateCatalogXMLWhenFilePathEmptyThenRuntimeException() {
        Exception exception = assertThrows(CatalogGenerationException.class, () -> catalogGeneration.generateXMLCatalog(catalog, ""));
        assertEquals("Output file need to be exist to generate the catalog file!", exception.getMessage());
    }
}