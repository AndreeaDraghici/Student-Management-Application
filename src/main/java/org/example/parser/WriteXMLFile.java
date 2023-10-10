package org.example.parser;
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

import org.example.model.Catalog;
import org.example.model.Materie;
import org.example.model.Nota;
import org.example.model.Situatie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

    public void createXMLFrom(Catalog catalog, String outputFile) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element root = doc.createElement("Catalog");
        doc.appendChild(root);
        for (Situatie situatie : catalog.getSituatii()) {
            Element elem = doc.createElement("Student");
            elem.setAttribute("id", Integer.toString(situatie.getStudent().getId()));
            elem.setAttribute("nume", situatie.getStudent().getNume());
            elem.setAttribute("prenume", situatie.getStudent().getPrenume());
            elem.setAttribute("sex", situatie.getStudent().getSex());

            for (Nota nota : situatie.getNote()) {
                Materie materie = this.getMaterieFrom(catalog.getMaterii(), nota.getIdMaterie());
                if (materie != null) {
                    Element notaElem = doc.createElement("Materie");
                    notaElem.setAttribute("id", Integer.toString(materie.getId()));
                    notaElem.setAttribute("denumire", materie.getDenumire());
                    notaElem.setAttribute("profesor", materie.getProfesor());
                    notaElem.setAttribute("an", materie.getAn());
                    notaElem.setAttribute("sem", Integer.toString(materie.getSem()));
                    notaElem.setTextContent(Float.toString(nota.getNota()));
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

    private Materie getMaterieFrom(List<Materie> materii, int idMaterie) {
        for (Materie materie : materii) {
            if (materie.getId() == idMaterie) {
                return materie;
            }
        }
        return null;
    }
}
