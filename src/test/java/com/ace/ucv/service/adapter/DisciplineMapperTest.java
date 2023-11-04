package com.ace.ucv.service.adapter;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.xml.materie.MateriaType;
import com.ace.ucv.model.xml.materie.MateriiType;
import com.ace.ucv.service.DisciplineMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
class DisciplineMapperTest {

    @Test
    void test_adaptXmlDisciplineTypeToDisciplineList_validMateriiType() {
        // Arrange
        DisciplineMapper disciplineMapper = new DisciplineMapper();
        MateriiType materiiType = new MateriiType();
        MateriaType materiaType = new MateriaType();
        materiaType.setDenumire("Math");
        materiaType.setProfesor("John Doe");
        materiaType.setAn("2023");
        materiaType.setSemestru("1");
        materiaType.setId("1");
        materiiType.getMateria().add(materiaType);

        // Act
        List<Discipline> disciplineList = disciplineMapper.adaptXmlDisciplineTypeToDisciplineList(materiiType);

        // Assert
        assertEquals(1, disciplineList.size());

        Discipline discipline = disciplineList.get(0);
        assertEquals("Math", discipline.getName());
        assertEquals("John Doe", discipline.getTeacher());
        assertEquals("2023", discipline.getYear());
        assertEquals(1, discipline.getSemester());
        assertEquals(1, discipline.getId());
    }

    @Test
    void test_adaptXmlDisciplineTypeToDisciplineList_nullMateriiType() {
        // Arrange
        DisciplineMapper disciplineMapper = new DisciplineMapper();

        // Act and Assert
        assertThrows(RuntimeException.class, () -> disciplineMapper.adaptXmlDisciplineTypeToDisciplineList(null));
    }

    @Test
    void test_adaptXmlObjectToDisciplineIntermediaryObject_nullFields() {
        // Arrange
        DisciplineMapper disciplineMapper = new DisciplineMapper();
        MateriaType materiaType = new MateriaType();
        materiaType.setAn("2");
        materiaType.setDenumire("Name");
        materiaType.setId("1");
        materiaType.setProfesor("Teacher");
        materiaType.setSemestru("1");

        // Act
        Discipline discipline = disciplineMapper.adaptXmlObjectToDisciplineIntermediaryObject(materiaType);

        // Assert
        assertEquals("Name", discipline.getName());
        assertEquals("Teacher", discipline.getTeacher());
        assertEquals("2", discipline.getYear());
        assertEquals(1, discipline.getSemester());
        assertEquals(1, discipline.getId());
    }

}