//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.10.27 at 07:19:00 PM EEST 
//


package com.ace.ucv.model.xml.nota;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nota package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Note_QNAME = new QName("", "note");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nota
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NoteType }
     * 
     */
    public NoteType createNoteType() {
        return new NoteType();
    }

    /**
     * Create an instance of {@link NotaStudType }
     * 
     */
    public NotaStudType createNotaStudType() {
        return new NotaStudType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoteType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "note")
    public JAXBElement<NoteType> createNote(NoteType value) {
        return new JAXBElement<NoteType>(_Note_QNAME, NoteType.class, null, value);
    }

}
