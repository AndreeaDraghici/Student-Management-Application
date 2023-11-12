package com.ace.ucv.service.output;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
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
public class CatalogBuilder {

    private static final Logger logger = LogManager.getLogger(CatalogBuilder.class);

    /**
     * Creates an XML document representing the catalog based on the provided lists of students, disciplines, and grades.
     *
     * @return The created XML document.
     */
    public Document createXmlDocument(Catalog catalog) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element catalogElement = document.createElement(TAG_NAME);
            document.appendChild(catalogElement);

            for (Student student : catalog.getStudents()) {
                addStudentInfo(document, catalogElement, student, catalog);
            }

            return document;

        } catch (Exception e) {
            logger.error(String.format("Failed to build the xml document representing the catalog due to: %s", e.getMessage()));
            return null;
        }
    }


    /**
     * Saves the provided XML document to a file at the specified file path.
     *
     * @param document The XML document to save.
     * @param filePath The path where the XML document will be saved.
     * @throws Exception If an error occurs during document saving.
     */
    public void saveXmlDocument(Document document, String filePath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(filePath));

        transformer.transform(domSource, streamResult);
    }


    /**
     * Adds information about a student, including associated disciplines and grades, to the catalog XML document.
     *
     * @param document       The XML document.
     * @param catalogElement The catalog element in the XML document.
     * @param student        The student whose information will be added.
     */
    private void addStudentInfo(Document document, Element catalogElement, Student student, Catalog catalog) {
        Element studentElement = document.createElement(STUDENT);
        catalogElement.appendChild(studentElement);

        createElementWithTextNode(document, studentElement, STUDENT_ID, String.valueOf(student.getId()));
        createElementWithTextNode(document, studentElement, STUDENT_NAME, student.getName());
        createElementWithTextNode(document, studentElement, STUDENT_SURNAME, student.getSurname());
        createElementWithTextNode(document, studentElement, STUDENT_PHONE, student.getPhone());
        createElementWithTextNode(document, studentElement, STUDENT_GENDER, String.valueOf(student.getGenre()));

        addStudentGrades(document, student, catalog, studentElement);
    }


    /**
     * Adds information about a student's grades, including associated disciplines and grades, to the catalog XML document.
     *
     * @param document       The XML document.
     * @param student        The student whose grades information will be added.
     * @param studentElement The student element in the XML document.
     */
    private void addStudentGrades(Document document, Student student, Catalog catalog, Element studentElement) {
        for (Grade grade : catalog.getGrades()) {
            if (grade.getStudentId() == student.getId()) {
                Discipline discipline = findDisciplineById(catalog.getDisciplines(), grade);
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

        createElementWithTextNode(document, disciplineElement, DISCIPLINE_ID, String.valueOf(discipline.getId()));
        createElementWithTextNode(document, disciplineElement, DISCIPLINE_NAME, discipline.getName());
        createElementWithTextNode(document, disciplineElement, DISCIPLINE_TEACHER, discipline.getTeacher());
        createElementWithTextNode(document, disciplineElement, DISCIPLINE_YEAR, discipline.getYear());
        createElementWithTextNode(document, disciplineElement, DISCIPLINE_SEMESTER, String.valueOf(discipline.getSemester()));

        disciplineElement.setAttribute(GRADE, String.valueOf(grade.getGradeValue()));
    }

    /**
     * Creates an XML element with a text node and appends it to the specified parent element.
     *
     * @param document The XML document.
     * @param parent   The parent element to which the new element will be appended.
     * @param tagName  The name of the new element.
     * @param text     The text content of the new element.
     */
    private void createElementWithTextNode(Document document, Element parent, String tagName, String text) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(text));
        parent.appendChild(element);
    }


    /**
     * Finds a discipline in the list of disciplines based on the subject ID from the grade.
     *
     * @param disciplines List of disciplines.
     * @param grade       The grade containing the subject ID.
     * @return The discipline associated with the subject ID, or a new Discipline object if not found.
     */
    public Discipline findDisciplineById(List<Discipline> disciplines, Grade grade) {
        return disciplines.stream()
                .filter(entry -> entry.getId() == grade.getSubjectId())
                .findFirst()
                .orElse(new Discipline());
    }

}
