package com.ace.ucv.parser;
import java.util.ArrayList;
import java.util.List;

import com.ace.ucv.model.Nota;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class ReadNotaXMLFile  extends DefaultHandler{

    private boolean bNota;
    private boolean bStudent;
    private boolean bMaterie;

    private Nota nota;
    private List<Nota> note;

    public List<Nota> getNote(){
        return note;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        if(qName.equalsIgnoreCase("Nota_stud")) {
            nota = new Nota();
        } else if (qName.equalsIgnoreCase("nota")) {
            bNota = true;
        } else if (qName.equalsIgnoreCase("materie")) {
            bMaterie = true;
        } else if (qName.equalsIgnoreCase("student")) {
            bStudent = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        if(bNota) {
            nota.setNota(Float.parseFloat(new String(ch, start, length)));
        } else if(bStudent) {
            nota.setIdStudent(Integer.parseInt(new String(ch, start, length)));
        } else if(bMaterie) {
            nota.setIdMaterie(Integer.parseInt(new String(ch, start, length)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        if(qName.equalsIgnoreCase("Nota_stud")) {
            if(note == null) {
                note = new ArrayList<Nota>();
            }
            note.add(nota);
            nota = null;
        } else if (qName.equalsIgnoreCase("nota")) {
            bNota = false;
        } else if (qName.equalsIgnoreCase("student")) {
            bStudent = false;
        } else if (qName.equalsIgnoreCase("materie")) {
            bMaterie = false;
        }
    }
}
