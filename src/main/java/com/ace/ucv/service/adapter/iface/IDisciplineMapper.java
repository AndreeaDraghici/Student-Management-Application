package com.ace.ucv.service.adapter.iface;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.xml.materie.MateriaType;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */

public interface IDisciplineMapper {

    /**
     * Adapts an XML object of type MateriaType to a Discipline intermediary object.
     *
     * @param materiaType The XML object representing a discipline.
     * @return The Discipline intermediary object.
     */
    Discipline adaptXmlObjectToDisciplineIntermediaryObject(MateriaType materiaType);
}
