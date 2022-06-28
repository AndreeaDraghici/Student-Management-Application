package frame;

import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditareStudentiFrame extends JFrame {
    private Student student;
    private JTextField txtNume;
    private JTextField txtPrenume;
    private JTextField txtSex;
    private JTextField txtTelefon;

    public EditareStudentiFrame(Student temp) {
        super("Editare Student: "+temp.getNume()+" "+temp.getPrenume());
        student = new Student();
        student.setId(temp.getId());
        student.setNume(temp.getNume());
        student.setPrenume(temp.getPrenume());
        student.setSex(temp.getSex());
        student.setTelefon(temp.getTelefon());

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel lbl = new JLabel("Id");
        lbl.setBounds(10, 10, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Nume");
        lbl.setBounds(10, 40, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Prenume");
        lbl.setBounds(10, 70, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Sex");
        lbl.setBounds(10, 100, 85, 20);
        panel.add(lbl);
        lbl = new JLabel("Telefon");
        lbl.setBounds(10, 130, 85, 20);
        panel.add(lbl);

        lbl = new JLabel(""+student.getId());
        lbl.setBounds(110, 10, 150, 20);
        panel.add(lbl);

        txtNume = new JTextField(student.getNume());
        txtNume.setBounds(110, 40, 150, 20);
        panel.add(txtNume);

        txtPrenume = new JTextField(student.getPrenume());
        txtPrenume.setBounds(110, 70, 150, 20);
        panel.add(txtPrenume);

        txtSex = new JTextField(student.getSex());
        txtSex.setBounds(110, 100, 150, 20);
        panel.add(txtSex);

        txtTelefon = new JTextField(""+student.getTelefon());
        txtTelefon.setBounds(110, 130, 150, 20);
        panel.add(txtTelefon);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //EditareAngajatForm.this.setVisible(false);
                //parent.reloadTable();
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                EditareStudentiFrame.this.setVisible(false);
            }
        });
        btnCancel.setBounds(10, 170, 90, 40);
        panel.add(btnCancel);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //EditareAngajatForm.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                //parent.getModel().setValueAt(txtName.getText(), 0, 0);
                //parent.getModel().addTableModelListener();
                //parent.reloadTable();
                EditareStudentiFrame.this.setVisible(false);
            }
        });
        btnSave.setBounds(110, 170, 90, 40);
        panel.add(btnSave);
    }

    public Student getStudent() {
        return student;
    }
}
