package com.ace.ucv.model.data;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Materie;
import com.ace.ucv.model.Nota;
import com.ace.ucv.model.Situatie;

import javax.swing.table.DefaultTableModel;

public class CatalogModel extends DefaultTableModel {

    private Catalog catalog;

    public CatalogModel(Catalog catalog) {
        super();
        this.catalog = catalog;
    }

    @Override
    public int getRowCount() {
        return catalog == null ? 0 : catalog.getSituatii().stream().mapToInt(s -> s.getNote().size()).sum();
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

    private Materie getMaterie(int idMaterie) {
        return catalog.getMaterii().stream().filter(m -> m.getId() == idMaterie).findFirst().orElse(null);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = 0;
        for (Situatie s : catalog.getSituatii()) {
            for (Nota nota : s.getNote()) {
                if (i == rowIndex) {
                    Materie m = getMaterie(nota.getIdMaterie());
                    if (m != null && m.getId() == 3) {
                        return getObject(columnIndex, s, nota, m);
                    }
                }
                i++;
            }
        }
        return null;
    }

    private static Object getObject(int columnIndex, Situatie s, Nota nota, Materie m) {
        switch (columnIndex) {
            case 0:
                return s.getStudent().getNume();
            case 1:
                return s.getStudent().getPrenume();
            case 2:
                return m.getDenumire();
            case 3:
                return nota.getNota();
            default:
                throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
    }
}
