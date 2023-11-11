package com.ace.ucv.utils;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Andreea Draghici on 11/11/2023
 * Name of project: StudentManagement
 */
public class PathChooser {
    public PathChooser() {
    }

    public File chooseOuputDirectoryAndSaveTheCatalogFile(AnchorPane root) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose output directory and save the report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        return fileChooser.showSaveDialog(root.getScene().getWindow());
    }

    public FileChooser getFileChooser(String fileType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(String.format("Select %s File", fileType));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }
}
