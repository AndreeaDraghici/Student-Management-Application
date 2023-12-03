package com.ace.ucv.application.cli.builder;

import com.ace.ucv.model.cli.Argument;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Andreea Draghici on 12/2/2023
 * Name of project: StudentManagement
 */

/*
 * ArgumentValidator class is responsible for validating the arguments provided to the application.
 */
public class ArgumentValidator {


    /**
     * Validates the provided arguments to ensure that the grade file path, student file path,
     * discipline file path, and output file path exist.
     *
     * @param argument - Argument object containing file paths
     * @throws IllegalArgumentException if any of the required file paths are empty or do not exist
     */
    public void validateArguments(Argument argument) {
        if (argument.getGradeFilePath().isEmpty()) {
            throw new IllegalArgumentException("The grade file path must exist! Add the argument with grade file path.");
        }

        if (!Files.exists(Paths.get(argument.getGradeFilePath()))) {
            throw new IllegalArgumentException("Grade file path must exist!");
        }

        if (argument.getStudentFilePath().isEmpty()) {
            throw new IllegalArgumentException("The student file path must exist! Add the argument with student file path.");
        }

        if (!Files.exists(Paths.get(argument.getStudentFilePath()))) {
            throw new IllegalArgumentException("Student file path must exist!");
        }

        if (argument.getDisciplineFilePath().isEmpty()) {
            throw new IllegalArgumentException("The discipline file path must exist! Add the argument with discipline file path.");
        }

        if (!Files.exists(Paths.get(argument.getDisciplineFilePath()))) {
            throw new IllegalArgumentException("Discipline file path must exist!");
        }

        if (argument.getOutputCatalogPath().isEmpty()) {
            throw new IllegalArgumentException("The output file path must exist! Add the argument with discipline file path.");
        }

        if (!Files.exists(Paths.get(argument.getOutputCatalogPath()))) {
            throw new IllegalArgumentException("Output file path must exist!");
        }
    }
}
