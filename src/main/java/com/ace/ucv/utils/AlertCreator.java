package com.ace.ucv.utils;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * Created by Andreea Draghici on 11/11/2023
 * Name of project: StudentManagement
 */
public class AlertCreator {

    public AlertCreator() {
    }

    public void createWarningModal(AnchorPane root, Exception exception) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(root.getScene().getWindow());
        alert.setTitle("Warning Dialog Box");
        alert.setHeaderText("Warning");
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }

    public void createErrorModal(AnchorPane root, Exception exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(root.getScene().getWindow());
        alert.setTitle("Error Dialog Box");
        alert.setHeaderText("Error");
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }
}
