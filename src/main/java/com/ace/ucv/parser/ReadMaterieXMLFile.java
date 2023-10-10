package com.ace.ucv.parser;
import java.util.ArrayList;
import java.util.List;

import com.ace.ucv.model.Materie;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadMaterieXMLFile extends DefaultHandler {

    private boolean bDenumire;
    private boolean bProfesor;
    private boolean bSem;
    private boolean bAn;

    private Materie materie;
    private List<Materie> materii;

    public List<Materie> getMaterii() {
        return materii;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase("Materia")) {
            materie = new Materie();
            if (attributes.getValue("id") != null) {
                materie.setId(Integer.parseInt(attributes.getValue("id")));
            }
        } else if (qName.equalsIgnoreCase("denumire")) {
            bDenumire = true;
        } else if (qName.equalsIgnoreCase("profesor")) {
            bProfesor = true;
        } else if (qName.equalsIgnoreCase("an")) {
            bAn = true;
        } else if (qName.equalsIgnoreCase("semestru")) {
            bSem = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        if (bDenumire) {
            materie.setDenumire(new String(ch, start, length));
        } else if (bProfesor) {
            materie.setProfesor(new String(ch, start, length));
        } else if (bSem) {
            materie.setSem(Integer.parseInt(new String(ch, start, length)));
        } else if (bAn) {
            materie.setAn(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase("Materia")) {
            if (materii == null) {
                materii = new ArrayList<Materie>();
            }
            materii.add(materie);
            materie = null;
        } else if (qName.equalsIgnoreCase("denumire")) {
            bDenumire = false;
        } else if (qName.equalsIgnoreCase("Profesor")) {
            bProfesor = false;
        } else if (qName.equalsIgnoreCase("Semestru")) {
            bSem = false;
        } else if (qName.equalsIgnoreCase("An")) {
            bAn = false;
        }
    }
}
