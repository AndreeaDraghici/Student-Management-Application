package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Grade;
import com.ace.ucv.model.xml.nota.NotaStudType;
import com.ace.ucv.model.xml.nota.NoteType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class GradeMapperTest {
    private GradeMapper gradeMapper;

    @BeforeEach
    void setUp() {
        gradeMapper = new GradeMapper();
    }

    @Test
    void testAdaptXmlGradeTypeToGradeListWhenNoteTypeIsValidThenReturnGradeList() {
        // Arrange
        NotaStudType notaStudType = new NotaStudType();
        notaStudType.setNota("10");
        notaStudType.setMaterie("1");
        notaStudType.setStudent("1");

        List<NotaStudType> notaStudList = new ArrayList<>();
        notaStudList.add(notaStudType);

        NoteType noteType = new NoteType();
        noteType.getNotaStud().addAll(notaStudList);

        // Act
        List<Grade> gradeList = gradeMapper.adaptXmlGradeTypeToGradeList(noteType);

        // Assert
        assertNotNull(gradeList);
        assertEquals(1, gradeList.size());
    }

    @Test
    void testAdaptXmlGradeTypeToGradeListWhenNoteTypeIsNullThenThrowRuntimeException() {
        // Act and Assert
        assertThrows(RuntimeException.class, () -> gradeMapper.adaptXmlGradeTypeToGradeList(null));
    }

    @Test
    void testAdaptXmlObjectToGradeIntermediaryObjectWhenNotaStudTypeIsValidThenReturnGrade() {
        // Arrange
        NotaStudType notaStudType = new NotaStudType();
        notaStudType.setNota("10");
        notaStudType.setMaterie("1");
        notaStudType.setStudent("1");

        // Act
        Grade grade = gradeMapper.adaptXmlObjectToGradeIntermediaryObject(notaStudType);

        // Assert
        assertNotNull(grade);
        assertEquals(10, grade.getGradeValue());
        assertEquals(1, grade.getSubjectId());
        assertEquals(1, grade.getStudentId());
    }

    @Test
    void testAdaptXmlObjectToGradeIntermediaryObjectWhenNotaStudTypeIsNullThenReturnGradeWithDefaultProperties() {
        // Act
        Grade grade = gradeMapper.adaptXmlObjectToGradeIntermediaryObject(null);

        // Assert
        assertNotNull(grade);
        assertEquals(0, grade.getGradeValue());
        assertEquals(0, grade.getSubjectId());
        assertEquals(0, grade.getStudentId());
    }
}