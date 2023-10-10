package org.example.model.frame;

import org.example.model.Materie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditareMaterieFrame extends JFrame {
    private Materie materie;
    private JTextField txtDenumire;
    private JTextField txtProf;
    private JTextField txtSem;
    private JTextField txtAn;

    public EditareMaterieFrame(Materie temp) {
        super("Editare :"+temp.getDenumire());
        materie = new Materie();
        materie.setId(temp.getId());
        materie.setDenumire(temp.getDenumire());
        materie.setProfesor(temp.getProfesor());
        materie.setAn(temp.getAn());
        materie.setSem(temp.getSem());

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel lbl = new JLabel("Id");
        lbl.setBounds(10, 10, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Denumire");
        lbl.setBounds(10, 40, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Profesor");
        lbl.setBounds(10, 70, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Semestru");
        lbl.setBounds(10, 100, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("An");
        lbl.setBounds(10, 130, 85, 20);
        panel.add(lbl);

        lbl = new JLabel(""+materie.getId());
        lbl.setBounds(110, 10, 150, 20);
        panel.add(lbl);

        txtDenumire = new JTextField(materie.getDenumire());
        txtDenumire.setBounds(110, 40, 150, 20);
        panel.add(txtDenumire);

        txtProf = new JTextField(materie.getProfesor());
        txtProf.setBounds(110, 70, 150, 20);
        panel.add(txtProf);

        txtSem = new JTextField(materie.getSem());
        txtSem.setBounds(110, 100, 150, 20);
        panel.add(txtSem);

        txtAn = new JTextField(""+materie.getAn());
        txtAn.setBounds(110, 130, 150, 20);
        panel.add(txtAn);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                EditareMaterieFrame.this.setVisible(false);
            }
        });
        btnCancel.setBounds(10, 170, 90, 40);
        panel.add(btnCancel);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                EditareMaterieFrame.this.setVisible(false);
            }
        });
        btnSave.setBounds(110, 170, 90, 40);
        panel.add(btnSave);
    }

    public Materie getMaterie() {
        return materie;
    }
}
