package com.ace.ucv.model.builder;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Student;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class EditareAdapter extends DefaultTableModel {

    private List<Student> students = new ArrayList<>();
    private List<Discipline> disciplines = new ArrayList<>();

    @Override
    public int getRowCount() {
        return isStudent() ? getStudentCount() : getDisciplineCount();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex > 4) {
            return "";
        }
        if (!isStudent()) {
            return getDisciplineColumnNames()[columnIndex];
        } else {
            return getStudentColumnNames()[columnIndex];
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        } else {
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!isStudent()) {
            return getDisciplineValue(rowIndex, columnIndex);
        } else {
            return getStudentValue(rowIndex, columnIndex);
        }
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    private boolean isStudent() {
        return students != null;
    }

    private int getStudentCount() {
        if (students == null) {
            return 0;
        }
        return students.size();
    }

    private int getDisciplineCount() {
        if (disciplines == null) {
            return 0;
        }
        return disciplines.size();
    }

    private String[] getStudentColumnNames() {
        return new String[]{"Id", "Nume", "Prenume", "Sex", "Telefon"};
    }

    private String[] getDisciplineColumnNames() {
        return new String[]{"Id", "Denumire", "Profesor", "An", "Semestru"};
    }

    private Object getStudentValue(int rowIndex, int columnIndex) {
        if (students != null && rowIndex < students.size() && columnIndex <= 4) {
            return buildStudent(rowIndex, columnIndex);
        }
        return new EditareAdapter();
    }


    private Object getDisciplineValue(int rowIndex, int columnIndex) {
        if (disciplines != null && rowIndex < disciplines.size() && columnIndex <= 5) {
            return buildDiscipline(rowIndex, columnIndex);
        }
        return new EditareAdapter();
    }

    private Object buildDiscipline(int rowIndex, int columnIndex) {
        Discipline discipline = disciplines.get(rowIndex);
        return establishDisciplineStructure(columnIndex, discipline);
    }

    private static Object establishDisciplineStructure(int columnIndex, Discipline discipline) {
        switch (columnIndex) {
            case 0:
                return discipline.getId();
            case 1:
                return discipline.getName();
            case 2:
                return discipline.getTeacher();
            case 3:
                return discipline.getYear();
            case 4:
                return discipline.getSemester();
            default:
                throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
    }

    private Object buildStudent(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        return establishStudentStructure(columnIndex, student);
    }

    private static Object establishStudentStructure(int columnIndex, Student student) {
        switch (columnIndex) {
            case 0:
                return student.getId();
            case 1:
                return student.getName();
            case 2:
                return student.getSurname();
            case 3:
                return student.getGenre();
            case 4:
                return student.getPhone();
            default:
                throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
    }
}
