package com.ace.ucv.service;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public class DisciplineMapper {

    private static final Logger logger = LogManager.getLogger(DisciplineMapper.class);

    /**
     * Adapt MateriiType xml object to a list of Discipline objects.
     *
     * @param materiiType The MateriiType object to be mapped.
     * @return A list of Discipline objects representing the mapped data.
     */
    public List<Discipline> adaptXmlDisciplineTypeToDisciplineList(MateriiType materiiType) {
        List<Discipline> disciplineList = new ArrayList<>();

        if (materiiType == null) {
            throw new RuntimeException("XML object type cannot be null!");
        }

        try {
            if (materiiType.getMateria() != null) {
                for (MateriaType materieType : materiiType.getMateria()) {
                    Discipline discipline = adaptXmlObjectToDisciplineIntermediaryObject(materieType);
                    disciplineList.add(discipline);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("Failed to adapt xml discipline to intermediary data model due to: %s", e.getMessage()));
        }

        return disciplineList;
    }

    /**
     * Adapt a MaterieType xml object to a Discipline object.
     *
     * @param materieType The MaterieType object to be mapped.
     * @return A Discipline object representing the mapped data.
     */
    public Discipline adaptXmlObjectToDisciplineIntermediaryObject(MateriaType materieType) {
        Discipline discipline = new Discipline();

        if (materieType != null) {

            discipline.setName(materieType.getDenumire());
            discipline.setTeacher(materieType.getProfesor());
            discipline.setYear(materieType.getAn());
            discipline.setSemester(Integer.parseInt(materieType.getSemestru()));
            discipline.setId(Integer.parseInt(materieType.getId()));

            logger.info("Mapped xml object to intermediary object.");
        }

        return discipline;
    }
}
