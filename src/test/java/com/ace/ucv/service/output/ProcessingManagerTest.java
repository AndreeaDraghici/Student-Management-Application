package com.ace.ucv.service.output;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingManagerTest {
    private ProcessingManager processingManager;
    private List<Discipline> disciplines;
    private Grade grade;
    private String filePath;

    @BeforeEach
    void setup() {
        processingManager = new ProcessingManager();
        disciplines = new ArrayList<>();
        grade = new Grade();
        filePath = "src/test/resources/out/test.xml";
    }

    @Test
    void testFindDisciplineByIdWhenExistsThenReturnDiscipline() {
        // Arrange
        Discipline discipline1 = new Discipline(1, "Math", "Mr. Smith", 1, "2023");
        Discipline discipline2 = new Discipline(2, "Physics", "Mrs. Johnson", 2, "2023");
        disciplines.add(discipline1);
        disciplines.add(discipline2);
        grade.setSubjectId(1);

        // Act
        Discipline result = processingManager.findDisciplineById(disciplines, grade);

        // Assert
        assertEquals(discipline1, result);
    }

    @Test
    void testFindDisciplineByIdWhenNotExistsThenReturnNewDiscipline() {
        // Arrange
        Discipline discipline1 = new Discipline(1, "Math", "Mr. Smith", 1, "2023");
        Discipline discipline2 = new Discipline(2, "Physics", "Mrs. Johnson", 2, "2023");
        disciplines.add(discipline1);
        disciplines.add(discipline2);
        grade.setSubjectId(3);

        // Act
        Discipline result = processingManager.findDisciplineById(disciplines, grade);

        // Assert
        assertTrue(result.getId() == 0 && result.getName().equals("") && result.getTeacher().equals("") && result.getSemester() == 0 && result.getYear().equals(""));
    }

    @Test
    void testSaveXmlDocumentWhenValidDocumentThenSaveToFile() throws Exception {
        // Arrange
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe", "+40721111111", "M"));
        Document document = processingManager.createXmlDocument(students, disciplines, new ArrayList<>());

        // Act
        processingManager.saveXmlDocument(document, filePath);

        // Assert
        assertTrue(new File(filePath).exists());
    }
}
