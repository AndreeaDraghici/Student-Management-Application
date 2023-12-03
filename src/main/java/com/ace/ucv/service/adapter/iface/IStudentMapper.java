package com.ace.ucv.service.adapter.iface;

import com.ace.ucv.model.Student;
import com.ace.ucv.model.xml.student.StudentType;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
public interface IStudentMapper {

    /**
     * Adapts an XML object of type StudentType to a Student intermediary object.
     *
     * @param studentType The XML object representing a student.
     * @return The Student intermediary object.
     */
    Student adaptXmlObjectToStudentIntermediaryObject(StudentType studentType);
}
