package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Student;
import com.ace.ucv.model.xml.student.StudentType;
import com.ace.ucv.model.xml.student.StudentiType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class StudentMapper {

    private static final Logger logger = LogManager.getLogger(StudentMapper.class);

    /**
     * Maps a StudentiType xml object to a list of Student objects.
     *
     * @param studentiType The StudentiType object to be mapped.
     * @return A list of Student objects representing the mapped data.
     */
    public List<Student> adaptXmlStudentTypeToStudentList(StudentiType studentiType) {
        List<Student> studentList = new ArrayList<>();

        if (studentiType == null) {
            throw new RuntimeException("XML student object type cannot be null!");
        }

        if (studentiType.getStudent() == null) {
            throw new RuntimeException("XML list of student objects type cannot be null!");
        }

        try {
            for (StudentType studentType : studentiType.getStudent()) {
                getIntermediaryStudentList(studentList, studentType);
            }
        } catch (Exception e) {
            logger.error(String.format("Failed to adapt xml student to intermediary data model due to: %s", e.getMessage()));
        }
        return studentList;
    }

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
    public Student adaptXmlObjectToStudentIntermediaryObject(StudentType studentType) {
        Student student = new Student();

        if (studentType != null) {
            student.setId(Integer.parseInt(studentType.getId()));
            student.setName(studentType.getNume());
            student.setSurname(studentType.getPrenume());
            student.setPhone(studentType.getTelefon());
            student.setGenre(studentType.getSex());

            logger.info("Mapped xml object to intermediary object.");
        }
        return student;
    }

}
