package com.ace.ucv.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.ace.ucv.utils.GUIConstants.ICON_PATH;

/**
 * Created by Andreea Draghici on 11/11/2023
 * Name of project: StudentManagement
 */

/*
* Utility class for creating alert dialogs in the GUI application.
 */
public class AlertCreator {

    private static final Logger logger = LogManager.getLogger(AlertCreator.class);


    /**
     * Default constructor for AlertCreator.
     */
    public AlertCreator() {
    }


    /**
     * Creates and displays a warning modal dialog.
     *
     * @param contentText The content text of the warning dialog.
     */
    public void createWarningModal(String contentText) {
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            getAlertDefaultIcon(alert);
            alert.setTitle("Warning Dialog Box");
            alert.setHeaderText("Warning");
            expandTheDialogMessage(contentText, alert);
            alert.showAndWait();
        } catch (Exception e) {
            logger.warn(String.format("Failed to show the warning alert box due to: %s", e.getMessage()), e);
        }
    }

    /**
     * Creates and displays an error modal dialog.
     *
     * @param contentText The content text of the error dialog.
     */
    public void createErrorModal(String contentText) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            getAlertDefaultIcon(alert);
            alert.setTitle("Error Dialog Box");
            alert.setHeaderText("Error");
            expandTheDialogMessage(contentText, alert);
            alert.showAndWait();
        } catch (Exception e) {
            logger.warn(String.format("Failed to show the error alert box due to: %s", e.getMessage()), e);
        }
    }


    /**
     * Creates and displays an info modal dialog.
     *
     * @param contentText The content text of the Information dialog.
     */
    public void createInformationModal(String contentText) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            getAlertDefaultIcon(alert);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("Information");
            expandTheDialogMessage(contentText, alert);
            alert.showAndWait();
        } catch (Exception e) {
            logger.warn(String.format("Failed to show the information alert box due to: %s", e.getMessage()), e);
        }
    }


    // Expands the content of the dialog by adding a TextArea.
    private static void expandTheDialogMessage(String string, Alert alert) {
        TextArea textArea = new TextArea(string);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        alert.getDialogPane().setContent(textArea);
    }

    // Sets the default application icon for the alert dialog.
    private void getAlertDefaultIcon(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource(ICON_PATH)).toString()));
    }

}
