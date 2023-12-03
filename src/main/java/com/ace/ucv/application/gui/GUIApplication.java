package com.ace.ucv.application.gui;

import com.ace.ucv.application.gui.builder.SceneBuilder;
import com.ace.ucv.application.IApplication;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.ace.ucv.utils.GUIConstants.*;

/**
 * Created by Andreea Draghici on 11/7/2023
 * Name of project: StudentManagement
 */

/*
 * A class representing a graphical user interface (GUI) application.
 * Extends Application and implements the IApplication interface.
 */
public class GUIApplication extends Application implements IApplication {

    private static final Logger logger = LogManager.getLogger(GUIApplication.class);

    private final SceneBuilder sceneBuilder;

    private AnchorPane stackPane;

    private static Window window;

    public static Stage mainStage;

    /**
     * Constructs a new GuiApplication instance.
     * Initializes the SceneBuilder with the view path and icon path.
     */
    public GUIApplication() {
        this.sceneBuilder = new SceneBuilder(VIEW_PATH, ICON_PATH);
    }

    /**
     * Entry point for running the GUI application.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void run(String[] args) {
        launch(args);
    }

    /**
     * Overrides the start method of the Application class.
     * Initializes and displays the primary stage of the GUI application.
     *
     * @param primaryStage The primary stage of the application.
     * @throws Exception If an exception occurs during the initialization and display of the primary stage.
     */
    @SuppressFBWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIApplication.mainStage = primaryStage;
        logger.info("Application is running...");

        try {
            // Set up primary stage properties
            primaryStage.setTitle(String.format("%s%s", TOOL_NAME, TOOL_VERSION));
            primaryStage.getIcons().add(sceneBuilder.getIcon());
            primaryStage.setScene(sceneBuilder.buildScene());
            primaryStage.setResizable(false);
            primaryStage.show();
            setWindow(primaryStage);
        } catch (Exception exception) {
            logger.error(String.format("Could start the GUI application due to:  %s", exception.getMessage()));
        }
    }

    /**
     * Retrieves the current window associated with the GUI application.
     *
     * @return The current window.
     */
    public static Window getWindow() {
        return window.getScene().getWindow();
    }

    /**
     * Sets the window for the GUI application.
     *
     * @param window The window to set.
     */
    private static void setWindow(Window window) {
        GUIApplication.window = window;
    }

}