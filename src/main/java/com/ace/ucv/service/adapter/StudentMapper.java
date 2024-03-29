package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Student;
import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import com.ace.ucv.service.adapter.iface.IStudentMapper;
import com.ace.ucv.service.exception.ConfigurationMapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class StudentMapper implements IStudentMapper {

    private static final Logger logger = LogManager.getLogger(StudentMapper.class);

    /**
     * Maps a StudentiType xml object to a list of Student objects.
     *
     * @param studentiType The StudentiType object to be mapped.
     * @return A list of Student objects representing the mapped data.
     */
    public List<Student> adaptXmlStudentTypeToStudentList(StudentiType studentiType) {
        List<Student> studentList = new ArrayList<>();
        checkInput(studentiType);

        try {
            for (StudentType studentType : studentiType.getStudent()) {
                getIntermediaryStudentList(studentList, studentType);
            }
        } catch (Exception e) {
            throw new ConfigurationMapperException("Failed to adapt XML student to intermediary data model: " + e.getMessage(), e);
        }
        return studentList;
    }

    /**
     * Validates the StudentiType object and its associated list of students.
     *
     * @param studentiType The StudentiType object to be checked.
     * @throws ConfigurationMapperException Throws an exception if the StudentiType object or its list of students is null.
     */
    private void checkInput(StudentiType studentiType) {
        if (studentiType == null) {
            throw new ConfigurationMapperException("XML student object type cannot be null!");
        }

        if (studentiType.getStudent() == null) {
            throw new ConfigurationMapperException("XML list of student objects type cannot be null!");
        }
    }

    /**
     * Adds an adapted Student object to the list of intermediary Student objects.
     *
     * @param studentList The list of intermediary Student objects.
     * @param studentType The StudentType object to be adapted and added to the list.
     */
    private void getIntermediaryStudentList(List<Student> studentList, StudentType studentType) {
        Student student = adaptXmlObjectToStudentIntermediaryObject(studentType);
        studentList.add(student);
    }

    /**
     * Maps a StudentType xml object to a Student object.
     *
     * @param studentType The StudentType object to be mapped.
     * @return A Student object representing the mapped data.
     */
    @Override
    public Student adaptXmlObjectToStudentIntermediaryObject(StudentType studentType) {
        Student student = new Student();

        if (studentType != null) {
            buildStudent(studentType, student);
        }
        logger.info(String.format("Successfully mapped %s XML object to intermediary Student object.", Objects.requireNonNull(studentType).getNume()));
        return student;
    }

    /**
     * Builds a Student object based on the information from the adapted StudentType object.
     *
     * @param studentType The StudentType object containing information about the student.
     * @param student     The Student object to be built and populated.
     */
    private void buildStudent(StudentType studentType, Student student) {
        if (student == null) {
            throw new RuntimeException("Student object is null.");
        }

        if (studentType != null) {
            student.setId(Integer.parseInt(studentType.getId()));
            student.setName(studentType.getNume());
            student.setSurname(studentType.getPrenume());
            student.setPhone(studentType.getTelefon());
            student.setGenre(studentType.getSex());
        } else {
            throw new RuntimeException("Received null studentType while building Student object. ");
        }
    }

}
