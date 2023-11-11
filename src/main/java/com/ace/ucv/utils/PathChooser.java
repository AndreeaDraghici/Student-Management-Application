package com.ace.ucv.utils;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Andreea Draghici on 11/11/2023
 * Name of project: StudentManagement
 */

/*
 * Utility class for handling file path selection and configuration of FileChooser.
 */
public class PathChooser {

    /**
     * Default constructor for PathChooser.
     */
    public PathChooser() {
    }

    /**
     * Opens a FileChooser dialog to choose the output directory and save the catalog file.
     *
     * @param root The root AnchorPane used to display the FileChooser dialog.
     * @return The selected File for saving the catalog, or null if canceled.
     */
    public File chooseOuputDirectoryAndSaveTheCatalogFile(AnchorPane root) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose output directory and save the report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        return fileChooser.showSaveDialog(root.getScene().getWindow());
    }

    /**
     * Creates and configures a FileChooser based on the specified file type.
     *
     * @param fileType The type of file (e.g., "Student", "Discipline", "Grade").
     * @return The configured FileChooser.
     */
    public FileChooser getFileChooser(String fileType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(String.format("Select %s File", fileType));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }
}
