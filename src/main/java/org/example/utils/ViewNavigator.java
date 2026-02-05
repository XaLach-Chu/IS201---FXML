package org.example.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewNavigator {
    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }
    public static void switchView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ViewNavigator.class.getResource(fxmlFile)
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, Utilities.WIDTH, Utilities.HEIGHT);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
