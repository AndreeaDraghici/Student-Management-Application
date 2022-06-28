package controller;

import data.CatalogModel;
import frame.EditareDataModel;
import model.*;
import parser.ReadMaterieXMLFile;
import parser.ReadNotaXMLFile;
import parser.ReadStudentXMLFile;
import parser.WriteXMLFile;

import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class MainForm extends JFrame {
    private List<Student> studenti;
    private List<Materie> materii;
    private List<Nota> note;
    private Catalog catalog;
    private JTable tabelStudenti;
    private JTable tabelMaterii;
    private JTable tabelCatalog;

    public MainForm() {
        super("Catalog Studenti");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(MainForm.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    String filepath = f.getPath();
                    if (!filepath.endsWith("D:\\Data\\Facultate\\StudentManagementProject\\src\\view\\Student.xml")) {
                        JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul studentilor");
                    } else {
                        studenti = (List<Student>) MainForm.this.parseXML(0, filepath);
                        System.out.println("nr studenti: " + studenti.size());
                        EditareDataModel model = new EditareDataModel();
                        model.setStudenti(studenti);
                        tabelStudenti.setModel(model);
                    }
                }

            }
        });
        button.setText("Alege Student.xml");
        button.setBounds(10, 10, 150, 20);
        panel.add(button);
        add(panel);

        button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(MainForm.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    String filepath = f.getPath();
                    if (!filepath.endsWith("D:\\Data\\Facultate\\StudentManagementProject\\src\\view\\Materie.xml")) {
                        JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul materiilor");
                    } else {
                        materii = (List<Materie>) MainForm.this.parseXML(1, filepath);
                        EditareDataModel model = new EditareDataModel();
                        model.setMaterii(materii);
                        tabelMaterii.setModel(model);
                    }
                }

            }
        });
        button.setText("Alege Materie.xml");
        button.setBounds(10, 200, 150, 20);
        panel.add(button);
        add(panel);

        button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(MainForm.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    String filepath = f.getPath();
                    if (!filepath.endsWith("D:\\Data\\Facultate\\StudentManagementProject\\src\\view\\Nota.xml")) {
                        JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul notelor");
                    } else {
                        note = (List<Nota>) MainForm.this.parseXML(2, filepath);
                        if (note != null && materii != null && studenti != null) {
                            catalog = new Catalog();
                            catalog.setMaterii(materii);
                            for (Student stud : studenti) {
                                Situatie situatie = new Situatie();
                                situatie.setStudent(stud);
                                for (Nota nota : note) {
                                    if (nota.getIdStudent() == stud.getId()) {
                                        situatie.addNota(nota);
                                    }
                                }
                                catalog.addSituatie(situatie);
                                CatalogModel model = new CatalogModel(catalog);
                                tabelCatalog.setModel(model);
                            }
                        }
                    }
                }

            }
        });
        button.setText("Alege Nota.xml");
        button.setBounds(10, 400, 150, 20);
        panel.add(button);
        add(panel);

        tabelStudenti = new JTable();
        // tabel.setEditingColumn(5);

        JScrollPane sp = new JScrollPane(tabelStudenti);
        sp.setBounds(200, 10, 400, 170);
        panel.add(sp);

        // AngajatCellRender render = new AngajatCellRender();
        // jt.setDefaultRenderer(String.class, render);
        // jt.setDefaultRenderer(Integer.class, render);
        //
        // editCell = new EditCellRender();
        // jt.setDefaultRenderer(JButton.class, editCell);
        // jt.setDefaultEditor(JButton.class, editCell);
        // editCell.addActionListener(new ActionListener() {

        // @Override
        // public void actionPerformed(ActionEvent e) {
        // Main.this.editRow(jt.getEditingRow());
        // }
        // });

        // jt.setCellSelectionEnabled(true);

        tabelMaterii = new JTable();
        // tabel.setEditingColumn(5);

        sp = new JScrollPane(tabelMaterii);
        sp.setBounds(200, 200, 400, 170);
        panel.add(sp);

        tabelCatalog = new JTable();
        tabelCatalog.setAutoCreateRowSorter(true);

        sp = new JScrollPane(tabelCatalog);
        sp.setBounds(200, 400, 400, 300);
        panel.add(sp);

        JButton btnSave = new JButton();
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteXMLFile domWriter = new WriteXMLFile();
                try {
                    domWriter.createXMLFrom(catalog, "src/view/oCatalog.xml");
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnSave.setText("Save to XML");
        btnSave.setBounds(200, 720, 150, 20);
        panel.add(btnSave);

        setSize(650, 800);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public Object parseXML(int tip, String file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            if (tip == 0) {
                ReadStudentXMLFile saxReader = new ReadStudentXMLFile();
                parser.parse(file, saxReader);
                return saxReader.getStudenti();
            } else if (tip == 1) {
                ReadMaterieXMLFile saxReader = new ReadMaterieXMLFile();
                parser.parse(file, saxReader);
                return saxReader.getMaterii();
            } else if (tip == 2) {
                ReadNotaXMLFile saxReader = new ReadNotaXMLFile();
                parser.parse(file, saxReader);
                return saxReader.getNote();
            }
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
