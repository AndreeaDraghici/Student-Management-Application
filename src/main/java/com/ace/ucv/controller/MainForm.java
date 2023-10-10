package com.ace.ucv.controller;

import com.ace.ucv.model.*;
import com.ace.ucv.model.data.EditareDataModel;
import com.ace.ucv.parser.ReadStudentXMLFile;
import com.ace.ucv.parser.WriteXMLFile;
import org.example.model.*;
import com.ace.ucv.model.data.CatalogModel;
import com.ace.ucv.parser.ReadMaterieXMLFile;
import com.ace.ucv.parser.ReadNotaXMLFile;

import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
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
        panel.setBackground(Color.red);

        populateTableStudenti(panel);
        populateTableMaterie(panel);
        populateTableNote(panel);

        tabelStudenti = new JTable();

        JScrollPane sp = new JScrollPane(tabelStudenti);
        sp.setBounds(200, 10, 350, 170);
        panel.add(sp);

        tabelMaterii = new JTable();

        sp = new JScrollPane(tabelMaterii);
        sp.setBounds(200, 200, 350, 170);
        panel.add(sp);

        tabelCatalog = new JTable();
        tabelCatalog.setAutoCreateRowSorter(true);

        sp = new JScrollPane(tabelCatalog);
        sp.setBounds(200, 400, 350, 250);
        panel.add(sp);

        JButton btnSave = new JButton();
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteXMLFile domWriter = new WriteXMLFile();
                try {
                    domWriter.createXMLFrom(catalog, "D:\\Data\\StudentManagement\\src\\view\\Catalog.xml");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnSave.setText("Generate to XML");
        btnSave.setBounds(270, 700, 150, 20);
        btnSave.setBackground(Color.white);
        panel.add(btnSave);

        setSize(600, 790);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void populateTableNote(JPanel panel) {
        JButton button;
        button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(MainForm.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    String filepath = f.getPath();
                    if (!filepath.endsWith("D:\\Data\\StudentManagement\\src\\view\\Nota.xml")) {
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
                                tabelCatalog.setBackground(Color.yellow);
                            }
                        }
                    }
                }

            }
        });
        button.setText("Nota.xml");
        button.setBounds(20, 450, 120, 20);
        button.setBackground(Color.white);
        panel.add(button);
        add(panel);
    }

    private void populateTableMaterie(JPanel panel) {
        JButton button;
        button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(MainForm.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    String filepath = f.getPath();
                    if (!filepath.endsWith("D:\\Data\\StudentManagement\\src\\view\\Materie.xml")) {
                        JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul materiilor");
                    } else {
                        materii = (List<Materie>) MainForm.this.parseXML(1, filepath);
                        EditareDataModel model = new EditareDataModel();
                        model.setMaterii(materii);
                        tabelMaterii.setModel(model);
                        tabelMaterii.setBackground(Color.yellow);
                    }
                }

            }
        });
        button.setText("Materie.xml");
        button.setBounds(20, 250, 120, 20);
        button.setBackground(Color.white);
        panel.add(button);
        add(panel);
    }

    private void populateTableStudenti(JPanel panel) {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(MainForm.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    String filepath = f.getPath();
                    if (!filepath.endsWith("D:\\Data\\StudentManagement\\src\\view\\Student.xml")) {
                        JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul studentilor");
                    } else {
                        studenti = (List<Student>) MainForm.this.parseXML(0, filepath);
                        JOptionPane.showMessageDialog(MainForm.this, "nr studenti: " + studenti.size());
                        EditareDataModel model = new EditareDataModel();
                        model.setStudenti(studenti);
                        tabelStudenti.setModel(model);
                        tabelStudenti.setBackground(Color.yellow);
                    }
                }

            }
        });
        button.setText("Student.xml");
        button.setBounds(20, 35, 120, 20);
        button.setBackground(Color.white);
        panel.add(button);
        add(panel);
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
            e.printStackTrace();
            return null;
        }
    }

}
