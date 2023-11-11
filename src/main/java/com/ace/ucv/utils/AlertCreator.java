package com.ace.ucv.utils;

import com.ace.ucv.controller.MainViewController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
public class AlertCreator {

    private static final Logger logger = LogManager.getLogger(AlertCreator.class);

    public AlertCreator() {
    }

    public void createWarningModal(AnchorPane root, String contentText) {
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            getAlertDefaultIcon(alert);
            alert.setTitle("Warning Dialog Box");
            alert.setHeaderText("Warning");
            expandTheDialogExceptionMessage(contentText, alert);
            alert.showAndWait();
        } catch (Exception e) {
            logger.warn(String.format("Failed to show the warning alert box due to: %s", e.getMessage()), e);
        }
    }

    public void createErrorModal(AnchorPane root, String contentText) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            getAlertDefaultIcon(alert);
            alert.setTitle("Error Dialog Box");
            alert.setHeaderText("Error");
            expandTheDialogExceptionMessage(contentText, alert);
            alert.showAndWait();
        } catch (Exception e) {
            logger.warn(String.format("Failed to show the error alert box due to: %s", e.getMessage()), e);
        }
    }

    private static void expandTheDialogExceptionMessage(String string, Alert alert) {
        TextArea textArea = new TextArea(string);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        alert.getDialogPane().setContent(textArea);
    }


    private void getAlertDefaultIcon(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource(ICON_PATH)).toString()));
    }

}
