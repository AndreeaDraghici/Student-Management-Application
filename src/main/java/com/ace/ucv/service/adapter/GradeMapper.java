package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Grade;
import com.ace.ucv.model.xml.nota.NotaStudType;
import com.ace.ucv.model.xml.nota.NoteType;
import com.ace.ucv.service.adapter.iface.IGradeMapper;
import com.ace.ucv.service.exception.ConfigurationMapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class GradeMapper implements IGradeMapper {

    private static final Logger logger = LogManager.getLogger(GradeMapper.class);

    public GradeMapper() {
    }

    /**
     * Maps a NoteType object to a list of Grade objects.
     *
     * @param noteType The NoteType object to be mapped.
     * @return A list of Grade objects representing the mapped data.
     */
    public List<Grade> adaptXmlGradeTypeToGradeList(NoteType noteType) {
        List<Grade> gradeList = new ArrayList<>();
        checkInput(noteType);

        try {
            for (NotaStudType gradeType : noteType.getNotaStud()) {
                getIntermediaryGradeList(gradeList, gradeType);
            }
        } catch (Exception e) {
            throw new ConfigurationMapperException(String.format("Failed to adapt XML grade to intermediary data model. Unable to map the provided XML grade data due to: %s", e.getMessage()), e);
        }
        return gradeList;
    }

    /**
     * Validates the NoteType object and its associated list of grades.
     *
     * @param noteType The NoteType object to be checked.
     * @throws ConfigurationMapperException Throws an exception if the NoteType object or its list of grades is null.
     */
    private void checkInput(NoteType noteType) {
        if (noteType == null) {
            throw new ConfigurationMapperException("XML grade object type cannot be null!");
        }

        if (noteType.getNotaStud() == null) {
            throw new ConfigurationMapperException("XML list of grade objects type cannot be null!");
        }
    }

    /**
     * Adds an adapted Grade object to the list of intermediary Grade objects.
     *
     * @param gradeList The list to which the adapted Grade object will be added.
     * @param gradeType The NotaStudType object to be adapted and added to the list.
     */
    private void getIntermediaryGradeList(List<Grade> gradeList, NotaStudType gradeType) {
        Grade grade = adaptXmlObjectToGradeIntermediaryObject(gradeType);
        gradeList.add(grade);
    }

    /**
     * Adapt a NoteType xml object to a Grade object.
     *
     * @param gradeType The NoteType.Grade object to be mapped.
     * @return A Grade object representing the mapped data.
     */
    @Override
    public Grade adaptXmlObjectToGradeIntermediaryObject(NotaStudType gradeType) {
        Grade grade = new Grade();

        if (gradeType != null) {
            buildGrade(gradeType, grade);
        }
        logger.info("Successfully mapped XML object to intermediary Grade object.");
        return grade;
    }

    /**
     * Builds a Grade object based on the information from a NotaStudType object.
     *
     * @param gradeType The NotaStudType object containing grade information.
     * @param grade     The Grade object to be built.
     */
    private void buildGrade(NotaStudType gradeType, Grade grade) {
        if (gradeType != null) {
            checkGradeRules(grade, gradeType.getNota(), gradeType.getStudent(), gradeType.getMaterie());
        } else {
            throw new RuntimeException("Received null gradeType while building Grade object.");
        }
    }

    /**
     * Checks and sets grade-related rules.
     *
     * @param grade           The Grade object to be updated.
     * @param notaString      The grade value as a string.
     * @param studentIdString The student ID as a string.
     * @param materieString   The subject ID as a string.
     */
    private void checkGradeRules(Grade grade, String notaString, String studentIdString, String materieString) {
        setGradeValue(grade, notaString);
        setStudentId(grade, studentIdString);
        setSubjectId(grade, materieString);
    }

    /**
     * Sets the grade value on the Grade object if the input is valid.
     *
     * @param grade      The Grade object to be updated.
     * @param notaString The grade value as a string.
     */
    private void setGradeValue(Grade grade, String notaString) {
        if (isValidString(notaString)) {
            grade.setGradeValue(Integer.parseInt(notaString));
        }
    }

    /**
     * Sets the student ID on the Grade object if the input is valid.
     *
     * @param grade           The Grade object to be updated.
     * @param studentIdString The student ID as a string.
     */
    private void setStudentId(Grade grade, String studentIdString) {
        if (isValidString(studentIdString)) {
            grade.setStudentId(Integer.parseInt(studentIdString));
        }
    }

    /**
     * Sets the subject ID on the Grade object if the input is valid.
     *
     * @param grade         The Grade object to be updated.
     * @param materieString The subject ID as a string.
     */
    private void setSubjectId(Grade grade, String materieString) {
        if (isValidString(materieString)) {
            grade.setSubjectId(Integer.parseInt(materieString));
        }
    }

    /**
     * Checks if a string is valid (not null and not empty).
     *
     * @param inputString The input string to be checked.
     * @return True if the string is valid, false otherwise.
     */
    private boolean isValidString(String inputString) {
        return inputString != null && !inputString.isEmpty();
    }
}