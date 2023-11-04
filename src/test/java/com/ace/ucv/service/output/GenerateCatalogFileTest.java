package com.ace.ucv.service.output;


import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

class GenerateCatalogFileTest {

    private GenerateCatalogFile generateCatalogFile;
    private List<Student> students;
    private List<Discipline> disciplines;
    private List<Grade> grades;
    private String filePath;

    @BeforeEach
    void setUp() {
        generateCatalogFile = new GenerateCatalogFile();
        students = Arrays.asList(new Student(1, "John", "Doe", "+40721111111", "Male"));
        disciplines = Arrays.asList(new Discipline(1, "Math", "Mr. Smith", 1, "2023"));
        grades = Arrays.asList(new Grade(10, 1, 1));
        filePath = "src/test/resources/out/catalog.xml";
    }

    @Test
    void testGenerateCatalogXMLWhenValidDataThenCatalogFileGenerated() {
        generateCatalogFile.generateCatalogXML(students, disciplines, grades, filePath);
        File file = new File(filePath);
        assertTrue(file.exists());
    }

    @Test
    void testGenerateCatalogXMLWhenStudentsNullThenRuntimeException() {
        Exception exception = assertThrows(RuntimeException.class, () -> generateCatalogFile.generateCatalogXML(null, disciplines, grades, filePath));
        assertEquals("Catalog must have the associated students!", exception.getMessage());
    }

    @Test
    void testGenerateCatalogXMLWhenDisciplinesNullThenRuntimeException() {
        Exception exception = assertThrows(RuntimeException.class, () -> generateCatalogFile.generateCatalogXML(students, null, grades, filePath));
        assertEquals("Catalog must have the associated disciplines!", exception.getMessage());
    }

    @Test
    void testGenerateCatalogXMLWhenGradesNullThenRuntimeException() {
        Exception exception = assertThrows(RuntimeException.class, () -> generateCatalogFile.generateCatalogXML(students, disciplines, null, filePath));
        assertEquals("Discipline must have the associated grades!", exception.getMessage());
    }

    @Test
    void testGenerateCatalogXMLWhenFilePathEmptyThenRuntimeException() {
        Exception exception = assertThrows(RuntimeException.class, () -> generateCatalogFile.generateCatalogXML(students, disciplines, grades, ""));
        assertEquals("Output file need to be exist to generate the catalog file!", exception.getMessage());
    }

}