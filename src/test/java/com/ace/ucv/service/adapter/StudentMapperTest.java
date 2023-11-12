package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Student;
import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    public void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    void testAdaptXmlStudentTypeToStudentListWhenXmlStudentAndListNotNullThenReturnStudentList() {
        StudentType studentType = new StudentType();
        studentType.setId("1");
        studentType.setNume("John");
        studentType.setPrenume("Doe");
        studentType.setTelefon("+40761234567");
        studentType.setSex("Male");

        StudentiType studentiType = new StudentiType();
        studentiType.getStudent().add(studentType);

        List<Student> studentList = studentMapper.adaptXmlStudentTypeToStudentList(studentiType);

        assertEquals(1, studentList.size());
        assertEquals(1, studentList.get(0).getId());
        assertEquals("John", studentList.get(0).getName());
        assertEquals("Doe", studentList.get(0).getSurname());
        assertEquals("+40761234567", studentList.get(0).getPhone());
        assertEquals("Male", studentList.get(0).getGenre());
    }


    @Test
    void testAdaptXmlStudentTypeToStudentListWhenXmlListNullThenThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> studentMapper.adaptXmlStudentTypeToStudentList(null));
    }

    @Test
    void testAdaptXmlObjectToStudentIntermediaryObjectWhenXmlStudentNotNullThenReturnStudent() {
        StudentType studentType = new StudentType();
        studentType.setId("1");
        studentType.setNume("John");
        studentType.setPrenume("Doe");
        studentType.setTelefon("+40769378690");
        studentType.setSex("Male");

        Student student = studentMapper.adaptXmlObjectToStudentIntermediaryObject(studentType);

        assertEquals(1, student.getId());
        assertEquals("John", student.getName());
        assertEquals("Doe", student.getSurname());
        assertEquals("+40769378690", student.getPhone());
        assertEquals("Male", student.getGenre());
    }
}