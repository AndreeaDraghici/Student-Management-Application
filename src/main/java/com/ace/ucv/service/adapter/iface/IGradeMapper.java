package com.ace.ucv.service.adapter.iface;

import com.ace.ucv.model.Grade;
import com.ace.ucv.model.xml.nota.NotaStudType;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
public interface IGradeMapper {

    /**
     * Adapts an XML object of type NotaStudType to a Grade intermediary object.
     *
     * @param notaStudType The XML object representing a grade.
     * @return The Grade intermediary object.
     */
    Grade adaptXmlObjectToGradeIntermediaryObject(NotaStudType notaStudType);
}
