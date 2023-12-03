package com.ace.ucv.model.cli;


/**
 * Created by Andreea Draghici on 12/2/2023
 * Name of project: StudentManagement
 */
public class Argument {

    private String outputCatalogPath;
    private String studentFilePath;
    private String gradeFilePath;
    private String disciplineFilePath;


    public Argument() {
        this.disciplineFilePath = "";
        this.gradeFilePath = "";
        this.outputCatalogPath = "";
        this.studentFilePath = "";
    }

    public Argument(String outputCatalogPath, String studentFilePath, String gradeFilePath, String disciplineFilePath) {
        this.outputCatalogPath = outputCatalogPath;
        this.studentFilePath = studentFilePath;
        this.gradeFilePath = gradeFilePath;
        this.disciplineFilePath = disciplineFilePath;
    }

    public String getOutputCatalogPath() {
        return outputCatalogPath;
    }

    public void setOutputCatalogPath(String outputCatalogPath) {
        this.outputCatalogPath = outputCatalogPath;
    }

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public void setStudentFilePath(String studentFilePath) {
        this.studentFilePath = studentFilePath;
    }

    public String getGradeFilePath() {
        return gradeFilePath;
    }

    public void setGradeFilePath(String gradeFilePath) {
        this.gradeFilePath = gradeFilePath;
    }

    public String getDisciplineFilePath() {
        return disciplineFilePath;
    }

    public void setDisciplineFilePath(String disciplineFilePath) {
        this.disciplineFilePath = disciplineFilePath;
    }
}
