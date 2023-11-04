package com.ace.ucv.service.output;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingManagerTest {
    private ProcessingManager processingManager;
    private Catalog catalog;

    @BeforeEach
    void setUp() {
        processingManager = new ProcessingManager();
        catalog = new Catalog();
    }

    @Test
    void testCreateXmlDocumentWhenCatalogContainsStudentAndDisciplineThenReturnValidXmlDocument() throws Exception {
        Student student = new Student(1, "John", "Doe", "+40721111111", "M");
        Discipline discipline = new Discipline(1, "Math", "Mr. Smith", 1, "2023");
        Grade grade = new Grade(10, 1, 1);

        catalog.addStudent(student);
        catalog.addDiscipline(discipline);
        catalog.addGrade(grade);

        Document document = processingManager.createXmlDocument(catalog);

        NodeList studentNodes = document.getElementsByTagName("Student");
        assertEquals(1, studentNodes.getLength());

        Element studentElement = (Element) studentNodes.item(0);
        assertEquals("1", studentElement.getElementsByTagName("ID").item(0).getTextContent());
        assertEquals("John", studentElement.getElementsByTagName("Name").item(0).getTextContent());

        NodeList disciplineNodes = studentElement.getElementsByTagName("Discipline");
        assertEquals(1, disciplineNodes.getLength());

        Element disciplineElement = (Element) disciplineNodes.item(0);
        assertEquals("1", disciplineElement.getElementsByTagName("ID").item(0).getTextContent());
        assertEquals("Math", disciplineElement.getElementsByTagName("Name").item(0).getTextContent());
        assertEquals("10", disciplineElement.getAttribute("Grade"));
    }

    @Test
    void testCreateXmlDocumentWhenCatalogContainsStudentWithoutGradesThenReturnEmptyXmlDocument() throws Exception {
        Student student = new Student(1, "John", "Doe", "+40721111111", "M");
        catalog.addStudent(student);

        Document document = processingManager.createXmlDocument(catalog);

        NodeList studentNodes = document.getElementsByTagName("Student");
        assertEquals(1, studentNodes.getLength());

        Element studentElement = (Element) studentNodes.item(0);
        assertEquals("1", studentElement.getElementsByTagName("ID").item(0).getTextContent());
        assertEquals("John", studentElement.getElementsByTagName("Name").item(0).getTextContent());

        NodeList disciplineNodes = studentElement.getElementsByTagName("Discipline");
        assertEquals(0, disciplineNodes.getLength());
    }

    @Test
    void testCreateXmlDocumentWhenCatalogContainsMultipleStudentsAndDisciplinesThenReturnValidXmlDocument() throws Exception {
        Student student1 = new Student(1, "John", "Doe", "+40721111111", "M");
        Student student2 = new Student(2, "Jane", "Doe", "+40722222222", "F");
        Discipline discipline1 = new Discipline(1, "Math", "Mr. Smith", 1, "2023");
        Discipline discipline2 = new Discipline(2, "Physics", "Mrs. Smith", 2, "2023");
        Grade grade1 = new Grade(10, 1, 1);
        Grade grade2 = new Grade(9, 2, 2);

        catalog.addStudent(student1);
        catalog.addStudent(student2);
        catalog.addDiscipline(discipline1);
        catalog.addDiscipline(discipline2);
        catalog.addGrade(grade1);
        catalog.addGrade(grade2);

        Document document = processingManager.createXmlDocument(catalog);

        NodeList studentNodes = document.getElementsByTagName("Student");
        assertEquals(2, studentNodes.getLength());

        NodeList disciplineNodes = document.getElementsByTagName("Discipline");
        assertEquals(2, disciplineNodes.getLength());
    }

    @Test
    void testSaveXmlDocumentWhenDocumentIsSavedSuccessfullyThenNoExceptionIsThrown() throws Exception {
        Document document = processingManager.createXmlDocument(catalog);
        String filePath = "test.xml";

        assertDoesNotThrow(() -> processingManager.saveXmlDocument(document, filePath));

        File file = new File(filePath);
        assertTrue(file.exists());

        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveXmlDocumentWhenFilePathDoesNotExistThenExceptionIsThrown() {
        Document document = null;
        try {
            document = processingManager.createXmlDocument(catalog);
        } catch (Exception e) {
            fail(e);
        }
        String filePath = "/nonexistent/path/test.xml";

        Document finalDocument = document;
        assertThrows(Exception.class, () -> processingManager.saveXmlDocument(finalDocument, filePath));
    }

    @Test
    void testFindDisciplineByIdWhenSubjectIdIsFoundThenReturnDiscipline() {
        Discipline discipline = new Discipline(1, "Math", "Mr. Smith", 1, "2023");
        catalog.addDiscipline(discipline);
        Grade grade = new Grade(10, 1, 1);

        Discipline result = processingManager.findDisciplineById(catalog.getDisciplines(), grade);

        assertEquals(discipline, result);
    }

    @Test
    void testFindDisciplineByIdWhenSubjectIdIsNotFoundThenReturnNewDiscipline() {
        Discipline discipline = new Discipline(1, "Math", "Mr. Smith", 1, "2023");
        catalog.addDiscipline(discipline);
        Grade grade = new Grade(10, 1, 1);

        Discipline result = processingManager.findDisciplineById(catalog.getDisciplines(), grade);

        assertEquals(1, result.getId());
    }
}
