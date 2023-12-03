package com.ace.ucv.application.cli.builder;

import com.ace.ucv.model.cli.Argument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Andreea Draghici on 12/3/2023
 * Name of project: StudentManagement
 */
class ArgumentValidatorTest {
    @Test
    void test_throws_exception_when_student_file_path_is_empty() {
        // Arrange
        Argument argument = mock(Argument.class);
        when(argument.getGradeFilePath()).thenReturn("gradeFilePath");
        when(argument.getStudentFilePath()).thenReturn("");
        when(argument.getDisciplineFilePath()).thenReturn("disciplineFilePath");
        when(argument.getOutputCatalogPath()).thenReturn("outputCatalogPath");

        ArgumentValidator argumentValidator = new ArgumentValidator();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> argumentValidator.validateArguments(argument));
    }

    @Test
    void test_throws_exception_when_grade_file_path_is_empty() {
        // Arrange
        Argument argument = mock(Argument.class);
        when(argument.getGradeFilePath()).thenReturn("");
        when(argument.getStudentFilePath()).thenReturn("studentFilePath");
        when(argument.getDisciplineFilePath()).thenReturn("disciplineFilePath");
        when(argument.getOutputCatalogPath()).thenReturn("outputCatalogPath");

        ArgumentValidator argumentValidator = new ArgumentValidator();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> argumentValidator.validateArguments(argument));
    }

    @Test
    void test_validates_all_file_paths_absolute() {
        // Arrange
        Argument argument = mock(Argument.class);
        when(argument.getGradeFilePath()).thenReturn("./src/main/resources/input_files/Nota.xml");
        when(argument.getStudentFilePath()).thenReturn("./src/main/resources/input_files/Student.xml");
        when(argument.getDisciplineFilePath()).thenReturn("./src/main/resources/input_files/Materie.xml");
        when(argument.getOutputCatalogPath()).thenReturn("./src/main/resources/output/out.xml");

        ArgumentValidator argumentValidator = new ArgumentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> argumentValidator.validateArguments(argument));
    }

    @Test
    void test_validates_file_paths_with_spaces() {
        // Arrange
        Argument argument = mock(Argument.class);
        when(argument.getGradeFilePath()).thenReturn("grade File Path");
        when(argument.getStudentFilePath()).thenReturn("student File Path");
        when(argument.getDisciplineFilePath()).thenReturn("discipline File Path");
        when(argument.getOutputCatalogPath()).thenReturn("output File Path");

        ArgumentValidator argumentValidator = new ArgumentValidator();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> argumentValidator.validateArguments(argument));
    }
}