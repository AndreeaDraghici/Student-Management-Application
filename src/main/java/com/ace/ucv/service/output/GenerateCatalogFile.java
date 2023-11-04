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

import static com.ace.ucv.utils.CatalogFileConstants.*;


/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

public class GenerateCatalogFile {

    private static final Logger logger = LogManager.getLogger(GenerateCatalogFile.class);


    /**
     * Generates a catalog XML file based on the provided lists of students, disciplines, and grades.
     *
     * @param students   List of students.
     * @param disciplines List of disciplines.
     * @param grades      List of grades.
     * @param filePath    The path where the catalog XML file will be generated.
     */
    public void generateCatalogXML(List<Student> students, List<Discipline> disciplines, List<Grade> grades, String filePath) {

        inputChecks(students, disciplines, grades, filePath);

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element catalogElement = document.createElement(TAG_NAME);
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


    /**
     * Validates input parameters for generating the catalog XML file.
     *
     * @param students   List of students.
     * @param disciplines List of disciplines.
     * @param grades      List of grades.
     * @param filePath    The path where the catalog XML file will be generated.
     * @throws RuntimeException if any of the input parameters are invalid or missing.
     */
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


    /**
     * Adds information about a student, including associated disciplines and grades, to the catalog XML document.
     *
     * @param document         The XML document.
     * @param catalogElement   The catalog element in the XML document.
     * @param student          The student whose information will be added.
     * @param disciplines      List of disciplines.
     * @param grades           List of grades.
     */
    private void addStudentInfo(Document document, Element catalogElement, Student student, List<Discipline> disciplines, List<Grade> grades) {
        Element studentElement = document.createElement(STUDENT);
        catalogElement.appendChild(studentElement);

        Element nameElement = document.createElement(STUDENT_NAME);
        nameElement.appendChild(document.createTextNode(student.getName()));
        studentElement.appendChild(nameElement);

        Element surnameElement = document.createElement(STUDENT_SURNAME);
        surnameElement.appendChild(document.createTextNode(student.getSurname()));
        studentElement.appendChild(surnameElement);

        Element idElement = document.createElement(STUDENT_ID);
        idElement.appendChild(document.createTextNode(String.valueOf(student.getId())));
        studentElement.appendChild(idElement);

        Element genderElement = document.createElement(STUDENT_GENDER);
        genderElement.appendChild(document.createTextNode(student.getGenre()));
        studentElement.appendChild(genderElement);

        for (Grade grade : grades) {
            if (grade.getStudentId() == student.getId()) {
                Discipline discipline = findDisciplineById(disciplines, grade);
                addDisciplineInfo(document, studentElement, discipline, grade);
            }
        }
    }


    /**
     * Adds information about a discipline and associated grade to the student element in the catalog XML document.
     *
     * @param document       The XML document.
     * @param studentElement The student element in the XML document.
     * @param discipline     The discipline whose information will be added.
     * @param grade          The grade associated with the discipline.
     */
    private void addDisciplineInfo(Document document, Element studentElement, Discipline discipline, Grade grade) {
        Element disciplineElement = document.createElement(DISCIPLINE);
        studentElement.appendChild(disciplineElement);

        Element nameElement = document.createElement(DISCIPLINE_NAME);
        nameElement.appendChild(document.createTextNode(discipline.getName()));
        disciplineElement.appendChild(nameElement);

        Element idElement = document.createElement(DISCIPLINE_ID);
        idElement.appendChild(document.createTextNode(String.valueOf(discipline.getId())));
        disciplineElement.appendChild(idElement);

        Element teacherElement = document.createElement(DISCIPLINE_TEACHER);
        teacherElement.appendChild(document.createTextNode(discipline.getTeacher()));
        disciplineElement.appendChild(teacherElement);

        Element yearElement = document.createElement(DISCIPLINE_YEAR);
        yearElement.appendChild(document.createTextNode(discipline.getYear()));
        disciplineElement.appendChild(yearElement);

        Element semesterElement = document.createElement(DISCIPLINE_SEMESTER);
        semesterElement.appendChild(document.createTextNode(String.valueOf(discipline.getSemester())));
        disciplineElement.appendChild(semesterElement);

        disciplineElement.setAttribute(GRADE, String.valueOf(grade.getGrade()));
    }


    /**
     * Finds a discipline in the list of disciplines based on the subject ID from the grade.
     *
     * @param disciplines List of disciplines.
     * @param grade       The grade containing the subject ID.
     * @return The discipline associated with the subject ID, or a new Discipline object if not found.
     */
    private Discipline findDisciplineById(List<Discipline> disciplines, Grade grade) {
        return disciplines.stream()
                .filter(entry -> entry.getId() == grade.getSubjectId())
                .findFirst()
                .orElse(new Discipline());
    }

}