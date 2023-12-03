package com.ace.ucv.application.gui.builder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Andreea Draghici on 11/7/2023
 * Name of project: StudentManagement
 */

/*
 * A class responsible for building scenes and providing icons for a GUI application.
 */
public class SceneBuilder {

    private static final Logger logger = LogManager.getLogger(SceneBuilder.class);

    private final String viewPath;
    private final String iconPath;

    /**
     * Default constructor for SceneBuilder.
     * Initializes viewPath and iconPath to empty strings.
     */
    public SceneBuilder() {
        this.viewPath = "";
        this.iconPath = "";
    }

    /**
     * Parameterized constructor for SceneBuilder.
     * Initializes viewPath and iconPath with the provided values.
     *
     * @param viewPath The path to the FXML file representing the view of the scene.
     * @param iconPath The path to the image file representing the application icon.
     */
    public SceneBuilder(String viewPath, String iconPath) {
        this.viewPath = viewPath;
        this.iconPath = iconPath;
    }

    /**
     * Builds and returns a Scene based on the specified FXML view path.
     *
     * @return The constructed Scene.
     */
    public Scene buildScene() {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(viewPath));
        AnchorPane stackPane;

        try {
            stackPane = loader.load();
        } catch (IOException exception) {
            logger.error(String.format("An error occurred when building the scene: %s", exception.getMessage()));
            return null;
        }
        return new Scene(stackPane);
    }

    /**
     * Retrieves and returns the application icon as an Image.
     *
     * @return The application icon as an Image.
     */
    public Image getIcon() {
        return new Image(iconPath);
    }
}
