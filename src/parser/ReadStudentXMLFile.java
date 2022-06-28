package parser;

import model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ReadStudentXMLFile extends DefaultHandler {
    private boolean bNume;
    private boolean bPrenume;
    private boolean bTelefon;
    private boolean bSex;

    private Student student;
    private List<Student> studenti;

    public List<Student> getStudenti() {
        return studenti;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase("Student")) {
            student = new Student();
            if (attributes.getValue("Id") != null) {
                student.setId(Integer.parseInt(attributes.getValue("Id")));
            }
        } else if (qName.equalsIgnoreCase("Nume")) {
            bNume = true;
        } else if (qName.equalsIgnoreCase("Prenume")) {
            bPrenume = true;
        } else if (qName.equalsIgnoreCase("Sex")) {
            bSex = true;
        } else if (qName.equalsIgnoreCase("Telefon")) {
            bTelefon = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        if (bNume) {
            student.setNume(new String(ch, start, length));
        } else if (bPrenume) {
            student.setPrenume(new String(ch, start, length));
        } else if (bSex) {
            student.setSex(new String(ch, start, length));
        } else if (bTelefon) {
            student.setTelefon(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase("student")) {
            if (studenti == null) {
                studenti = new ArrayList<Student>();
            }
            studenti.add(student);
            student = null;
        } else if (qName.equalsIgnoreCase("Nume")) {
            bNume = false;
        } else if (qName.equalsIgnoreCase("Prenume")) {
            bPrenume = false;
        } else if (qName.equalsIgnoreCase("Sex")) {
            bSex = false;
        } else if (qName.equalsIgnoreCase("Telefon")) {
            bTelefon = false;
        }
    }
}
