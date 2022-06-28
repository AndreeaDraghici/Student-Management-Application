package data;

import model.Materie;
import model.Student;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EditareModel extends DefaultTableModel {
    private List<Student> studenti = null;
    private List<Materie> materii = null;
    private boolean isStudent = false;

    public void setMaterii(List<Materie> materii) {
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
        return  isStudent? studenti.size(): materii.size();
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
            Materie m = materii.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return m.getId();
                case 1:
                    return m.getDenumire();
                case 2:
                    return m.getProfesor();
                case 3:
                    return m.getAn();
                case 4:
                    return m.getSem();
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
                    return s.getNume();
                case 2:
                    return s.getPrenume();
                case 3:
                    return s.getSex();
                case 4:
                    return s.getTelefon();
            }
        }
        return null;
    }

}
