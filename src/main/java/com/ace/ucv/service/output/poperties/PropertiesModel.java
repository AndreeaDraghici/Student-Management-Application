package com.ace.ucv.service.output.poperties;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
public class PropertiesModel {

    private String studentPath;

    private String disciplinePath;

    private String gradePath;


    public PropertiesModel() {
        this.disciplinePath = "";
        this.gradePath = "";
        this.studentPath = "";
    }

    public PropertiesModel(String studentPath, String disciplinePath, String gradePath) {
        this.studentPath = studentPath;
        this.disciplinePath = disciplinePath;
        this.gradePath = gradePath;
  }


    public String getStudentPath() {
        return studentPath;
    }

    public void setStudentPath(String studentPath) {
        this.studentPath = studentPath;
    }

    public String getDisciplinePath() {
        return disciplinePath;
    }

    public void setDisciplinePath(String disciplinePath) {
        this.disciplinePath = disciplinePath;
    }

    public String getGradePath() {
        return gradePath;
    }

    public void setGradePath(String gradePath) {
        this.gradePath = gradePath;
    }
}
