package model.data;

import model.Catalog;
import model.Materie;
import model.Nota;
import model.Situatie;

import javax.swing.table.DefaultTableModel;

public class CatalogModel extends DefaultTableModel {

    private Catalog catalog = null;

    public CatalogModel(Catalog catalog) {
        super();
        this.catalog = catalog;
    }

    @Override
    public int getRowCount() {
        if(catalog == null) return 0;
        int r = 0;
        for (Situatie s : catalog.getSituatii()) {
            r += s.getNote().size();
        }
        return r;
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
//		case 2:
//			return "Medie";
            case 2:
                return "Materie";
            case 3:
                return "Nota";
        }

        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 2:
            case 3:
//			return Float.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return false;
    }

    private Materie getMaterie(int idMaterie) {
        for (Materie m : catalog.getMaterii()) {
            if (m.getId() == idMaterie)
                return m;
        }
        return null;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = 0;
        for (Situatie s : catalog.getSituatii()) {
            int n = s.getNote().size();
            if (i + n > rowIndex) {
                Nota nota = s.getNote().get(rowIndex - i);
                Materie m = getMaterie(nota.getIdMaterie());
                if(m.getId() == 3) {
                    switch (columnIndex) {
                        case 0:
                            return s.getStudent().getNume();
                        case 1:
                            return s.getStudent().getPrenume();
//				case 2:
//					return s.getMedie();
                        case 2:
                            return m.getDenumire();
                        case 3:
                            return nota.getNota();
                    }
                }
                return null;
            } else {
                i += n;
            }
        }
        return null;
    }




}
