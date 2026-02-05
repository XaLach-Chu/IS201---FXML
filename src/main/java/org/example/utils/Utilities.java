package org.example.utils;

import javafx.scene.control.Alert;

public class Utilities {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static void showAlertDialog(Alert.AlertType alertType, String msg) {
        Alert alert = new Alert(alertType);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
