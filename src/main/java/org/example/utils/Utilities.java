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

    public static String roleConvert(Integer role) {
        if (role == 1)
            return "LEARNER";
        else if (role == 2)
            return "MENTOR";
        else if (role == 3)
            return "SUPPORT";
        else if (role == 4)
            return "FINANCE";
        else if (role == 5)
            return "QA";
        else
            return "ADMIN";
    }
}
