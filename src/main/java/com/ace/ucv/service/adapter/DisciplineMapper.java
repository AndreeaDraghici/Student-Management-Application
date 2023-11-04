package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
import com.ace.ucv.service.exception.ConfigurationMapperException;
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
        checkInput(materiiType);

        try {
            for (MateriaType materieType : materiiType.getMateria()) {
                getIntermediaryDisciplineList(disciplineList, materieType);
            }
        } catch (Exception e) {
            throw new ConfigurationMapperException("Failed to adapt XML discipline to intermediary data model.", e);
        }
        return disciplineList;
    }


    /**
     * Validates the MateriiType object and its associated list of disciplines.
     *
     * @param materiiType The MateriiType object to be checked.
     * @throws ConfigurationMapperException Throws an exception if the MateriiType object or its list of disciplines is null.
     */
    private void checkInput(MateriiType materiiType) {
        if (materiiType == null) {
            throw new ConfigurationMapperException("XML discipline object type cannot be null!");
        }

        if (materiiType.getMateria() == null) {
            throw new ConfigurationMapperException("XML list of discipline objects type cannot be null!");
        }
    }

    /**
     * Converts a MateriaType object to a Discipline object and adds it to the provided list.
     *
     * @param disciplineList The list to which the converted Discipline object will be added.
     * @param materieType    The MateriaType object to be converted.
     */
    private void getIntermediaryDisciplineList(List<Discipline> disciplineList, MateriaType materieType) {
        Discipline discipline = adaptXmlObjectToDisciplineIntermediaryObject(materieType);
        disciplineList.add(discipline);
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
            buildDiscipline(materieType, discipline);
            logger.info("Mapped xml object to intermediary discipline object.");
        }
        return discipline;
    }


    /**
     * Builds a Discipline object from the provided MateriaType.
     *
     * @param materieType The MateriaType object containing discipline information.
     * @param discipline  The Discipline object to be built.
     */
    private void buildDiscipline(MateriaType materieType, Discipline discipline) {
        discipline.setName(materieType.getDenumire());
        discipline.setTeacher(materieType.getProfesor());
        discipline.setYear(materieType.getAn());
        discipline.setSemester(Integer.parseInt(materieType.getSemestru()));
        discipline.setId(Integer.parseInt(materieType.getId()));
    }
}
