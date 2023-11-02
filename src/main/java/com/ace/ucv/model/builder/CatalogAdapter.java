package com.ace.ucv.model.builder;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Situation;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.table.DefaultTableModel;

public class CatalogAdapter extends DefaultTableModel {

    private final Catalog catalog;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CatalogAdapter(Catalog catalog) {
        super();
        this.catalog = catalog;
    }

    @Override
    public int getRowCount() {
        return catalog == null ? 0 : catalog.getSituations().stream().mapToInt(s -> s.getGrades().size()).sum();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Nume";
            case 1:
                return "Prenume";
            case 2:
                return "Materie";
            case 3:
                return "Nota";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex == 2 || columnIndex == 3) ? String.class : Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    private Discipline getMaterie(int idMaterie) {
        return catalog.getDisciplines().stream().filter(m -> m.getId() == idMaterie).findFirst().orElse(null);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = 0;
        for (Situation s : catalog.getSituations()) {
            for (Grade grade : s.getGrades()) {
                if (i == rowIndex) {
                    Discipline m = getMaterie(grade.getSubjectId());
                    if (m != null && m.getId() == 3) {
                        return getObject(columnIndex, s, grade, m);
                    }
                }
                i++;
            }
        }
        return null;
    }

    private static Object getObject(int columnIndex, Situation situation, Grade grade, Discipline discipline) {
        switch (columnIndex) {
            case 0:
                return situation.getStudent().getName();
            case 1:
                return situation.getStudent().getSurname();
            case 2:
                return discipline.getName();
            case 3:
                return grade.getGrade();
            default:
                throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
    }
}
