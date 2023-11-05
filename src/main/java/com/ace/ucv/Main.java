package com.ace.ucv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Main extends Application {

    private static final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new File("D:\\Data\\Facultate\\Master\\An 1\\SEM 1\\MSIC\\Proiect\\StudentManagement\\src\\main\\resources\\views\\main_view.fxml").toURI().toURL());
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            logger.error(String.format("Failed to run the application due to: %s", e.getMessage()));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
