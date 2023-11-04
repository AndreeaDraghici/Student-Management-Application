/*
package com.ace.ucv.service.output;
import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.ace.ucv.model.Catalog;
import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Situation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

    public void createXMLFrom(Catalog catalog, String outputFile) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element root = doc.createElement("Catalog");
        doc.appendChild(root);
        for (Situation situation : catalog.getSituations()) {
            Element elem = doc.createElement("Student");
            elem.setAttribute("id", Integer.toString(situation.getStudent().getId()));
            elem.setAttribute("nume", situation.getStudent().getName());
            elem.setAttribute("prenume", situation.getStudent().getSurname());
            elem.setAttribute("sex", situation.getStudent().getGenre());

            for (Grade grade : situation.getGrades()) {
                Discipline discipline = this.getMaterieFrom(catalog.getDisciplines(), grade.getSubjectId());
                if (discipline != null) {
                    Element notaElem = doc.createElement("Materie");
                    notaElem.setAttribute("id", Integer.toString(discipline.getId()));
                    notaElem.setAttribute("denumire", discipline.getName());
                    notaElem.setAttribute("profesor", discipline.getTeacher());
                    notaElem.setAttribute("an", discipline.getYear());
                    notaElem.setAttribute("sem", Integer.toString(discipline.getSemester()));
                    notaElem.setTextContent(Float.toString(grade.getGrade()));
                    elem.appendChild(notaElem);
                }
            }
            root.appendChild(elem);
        }

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer t = tFactory.newTransformer();
        Result result = new StreamResult(new File(outputFile));
        Source source = new DOMSource(doc);
        t.transform(source, result);
    }

    private Discipline getMaterieFrom(List<Discipline> materii, int idMaterie) {
        for (Discipline discipline : materii) {
            if (discipline.getId() == idMaterie) {
                return discipline;
            }
        }
        return null;
    }
}
*/
