package org.example.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Main;

import java.io.IOException;

public class ViewNavigator {
    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }
    public static void switchView(String fxmlFile, boolean windowState) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ViewNavigator.class.getResource(fxmlFile)
            );
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

            if (windowState) {
                mainStage.setMaximized(false);
                mainStage.setMaximized(true);
            }
            else {
                mainStage.setMaximized(false);
                mainStage.setWidth(Utilities.WIDTH);
                mainStage.setHeight(Utilities.HEIGHT);
            }
            mainStage.centerOnScreen();

            mainStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
