package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.xml.nota.NotaStudType;
import com.ace.ucv.model.xml.nota.NoteType;
import com.ace.ucv.service.exception.ConfigurationMapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class GradeMapper {

    private static final Logger logger = LogManager.getLogger(GradeMapper.class);

    private List<Discipline> listOfDisciplines;

    public GradeMapper() {
        this.listOfDisciplines = new ArrayList<>();
    }

    public GradeMapper(List<Discipline> listOfDisciplines) {
        this.listOfDisciplines = listOfDisciplines;
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
            throw new ConfigurationMapperException("Failed to adapt xml grade to intermediary data model due to: ", e);
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
    public Grade adaptXmlObjectToGradeIntermediaryObject(NotaStudType gradeType) {
        Grade grade = new Grade();

        if (gradeType != null) {
            buildGrade(gradeType, grade);
            logger.info("Mapped xml object to intermediary grade object.");
        }
        return grade;
    }

    /**
     * Builds a Grade object based on the information from a NotaStudType object.
     *
     * @param gradeType The NotaStudType object containing grade information.
     * @param grade     The Grade object to be built.
     */
    private void buildGrade(NotaStudType gradeType, Grade grade) {
        String notaString = gradeType.getNota();
        String studentIdString = gradeType.getStudent();
        String materieString = gradeType.getMaterie();

        checkGradeRules(grade, notaString, studentIdString, materieString);
    }

    private void checkGradeRules(Grade grade, String notaString, String studentIdString, String materieString) {
        if (notaString != null && !notaString.isEmpty()) {
            grade.setGradeValue(Integer.parseInt(notaString));
        }

        if (studentIdString != null && !studentIdString.isEmpty()) {
            grade.setStudentId(Integer.parseInt(studentIdString));
        }

        if (materieString != null && !materieString.isEmpty()) {
            int subjectId = findSubjectIdByName(materieString);
            grade.setSubjectId(subjectId);
        }
    }

    private int findSubjectIdByName(String subjectName) {
        for (Discipline discipline : listOfDisciplines) {
            if (discipline.getName().equalsIgnoreCase(subjectName)) {
                return discipline.getId();
            }
        }
        return -1;
    }
}