package com.ace.ucv.service.output;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;


/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class GenerateCatalogFile {

    private static final Logger logger = LogManager.getLogger(GenerateCatalogFile.class);

    public void generateCatalogXML(List<Student> students, List<Discipline> disciplines, List<Grade> grades, String filePath) {

        inputChecks(students, disciplines, grades, filePath);

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element catalogElement = document.createElement("Catalog");
            document.appendChild(catalogElement);

            for (Student student : students) {
                addStudentInfo(document, catalogElement, student, disciplines, grades);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            transformer.transform(domSource, streamResult);

            logger.info("Catalog file was created!");
        } catch (Exception e) {
            logger.error(String.format("Failed to generate the output file due to: %s", e.getMessage()));
        }
    }

    private void inputChecks(List<Student> students, List<Discipline> disciplines, List<Grade> grades, String filePath) {
        if (students == null) {
            throw new RuntimeException("Catalog must have the associated students!");
        }

        if (disciplines == null) {
            throw new RuntimeException("Catalog must have the associated disciplines!");
        }

        if (grades == null) {
            throw new RuntimeException("Discipline must have the associated grades!");
        }

        if (filePath.isEmpty()) {
            throw new RuntimeException("Output file need to be exist to generate the catalog file!");
        }
    }

    private void addStudentInfo(Document document, Element catalogElement, Student student, List<Discipline> disciplines, List<Grade> grades) {
        Element studentElement = document.createElement("Student");
        catalogElement.appendChild(studentElement);

        Element nameElement = document.createElement("Name");
        nameElement.appendChild(document.createTextNode(student.getName()));
        studentElement.appendChild(nameElement);

        Element surnameElement = document.createElement("Surname");
        surnameElement.appendChild(document.createTextNode(student.getSurname()));
        studentElement.appendChild(surnameElement);

        Element idElement = document.createElement("ID");
        idElement.appendChild(document.createTextNode(String.valueOf(student.getId())));
        studentElement.appendChild(idElement);

        Element genderElement = document.createElement("Gender");
        genderElement.appendChild(document.createTextNode(student.getGenre()));
        studentElement.appendChild(genderElement);

        for (Grade grade : grades) {
            if (grade.getStudentId() == student.getId()) {
                Discipline discipline = findDisciplineById(disciplines, grade);
                addDisciplineInfo(document, studentElement, discipline, grade);
            }
        }
    }

    private void addDisciplineInfo(Document document, Element studentElement, Discipline discipline, Grade grade) {
        Element disciplineElement = document.createElement("Discipline");
        studentElement.appendChild(disciplineElement);

        Element nameElement = document.createElement("Name");
        nameElement.appendChild(document.createTextNode(discipline.getName()));
        disciplineElement.appendChild(nameElement);

        Element idElement = document.createElement("ID");
        idElement.appendChild(document.createTextNode(String.valueOf(discipline.getId())));
        disciplineElement.appendChild(idElement);

        Element teacherElement = document.createElement("Teacher");
        teacherElement.appendChild(document.createTextNode(discipline.getTeacher()));
        disciplineElement.appendChild(teacherElement);

        Element yearElement = document.createElement("Year");
        yearElement.appendChild(document.createTextNode(discipline.getYear()));
        disciplineElement.appendChild(yearElement);

        Element semesterElement = document.createElement("Semester");
        semesterElement.appendChild(document.createTextNode(String.valueOf(discipline.getSemester())));
        disciplineElement.appendChild(semesterElement);

        disciplineElement.setAttribute("Grade", String.valueOf(grade.getGrade()));
    }

    private Discipline findDisciplineById(List<Discipline> disciplines, Grade grade) {
        return disciplines.stream()
                .filter(entry -> entry.getId() == grade.getSubjectId())
                .findFirst()
                .orElse(new Discipline());
    }

}
