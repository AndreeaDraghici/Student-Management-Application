package com.ace.ucv.utils;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */

/**
 * Constants class for XML tags and attribute names used in the catalog file.
 */
public class CatalogFileConstants {

    /**
     * Catalog Element
     */
    public static final String TAG_NAME                  = "Catalog";


    /**
     * Student Element and Attributes
     */
    public static final String STUDENT                   = "Student";
    public static final String STUDENT_NAME              = "Name";
    public static final String STUDENT_SURNAME           = "Surname";
    public static final String STUDENT_ID                = "ID";
    public static final String STUDENT_GENDER            = "Gender";
    public static final String STUDENT_PHONE            = "Phone";


    /**
     * Discipline Element and Attributes
     */
    public static final String DISCIPLINE                = "Discipline";
    public static final String DISCIPLINE_NAME           = "Name";
    public static final String DISCIPLINE_ID             = "ID";
    public static final String DISCIPLINE_TEACHER        = "Teacher";
    public static final String DISCIPLINE_YEAR           = "Year";
    public static final String DISCIPLINE_SEMESTER       = "Semester";


    /**
     *   Grade Element
     */
    public static final String GRADE                     = "Grade";
}
