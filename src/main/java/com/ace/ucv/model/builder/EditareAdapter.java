package com.ace.ucv.model.builder;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Student;

import java.util.List;

import javax.swing.table.DefaultTableModel;
public class EditareAdapter extends DefaultTableModel{

    private List<Student> studenti = null;
    private List<Discipline> materii = null;
    private boolean isStudent = false;

    public void setMaterii(List<Discipline> materii) {
        this.materii = materii;
        isStudent = false;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
        isStudent = true;
    }

    @Override
    public int getRowCount() {
        if(studenti == null && materii == null)
            return 0;
        if (isStudent) {
            return studenti.size();
        }
        return materii.size();
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
        if (!isStudent) {
            switch (columnIndex) {
                case 0:
                    return "Id";
                case 1:
                    return "Denumire";
                case 2:
                    return "Profesor";
                case 3:
                    return "An";
                case 4:
                    return "Semestru";
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return "Id";
                case 1:
                    return "Nume";
                case 2:
                    return "Prenume";
                case 3:
                    return "Sex";
                case 4:
                    return "Telefon";
            }
        }

        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (!isStudent) {
            switch (columnIndex) {
                case 0:
                case 4:
                    return Integer.class;
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
            }
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return columnIndex == 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub

        if (!isStudent) {
            if(rowIndex >= materii.size() || columnIndex > 5) {
                return null;
            }
            Discipline m = materii.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return m.getId();
                case 1:
                    return m.getName();
                case 2:
                    return m.getTeacher();
                case 3:
                    return m.getYear();
                case 4:
                    return m.getSemester();
            }
        } else {
            if(rowIndex >= studenti.size() || columnIndex > 5) {
                return null;
            }
            Student s = studenti.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return s.getId();
                case 1:
                    return s.getName();
                case 2:
                    return s.getSurname();
                case 3:
                    return s.getGenre();
                case 4:
                    return s.getPhone();
            }
        }
        return null;
    }
}
