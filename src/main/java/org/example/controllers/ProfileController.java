package org.example.controllers;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.DirectionalLight;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.security.SessionManager;
import org.example.utils.ViewNavigator;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Button accountDetailsButton;
    public Button changePasswordButton;
    public Button historyButton;
    public Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonActions(accountDetailsButton);
        buttonActions(changePasswordButton);
        buttonActions(historyButton);
        buttonActions(logoutButton);

        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SessionManager.clearSession();
                ViewNavigator.switchView("/views/LoginView.fxml", false);
            }
        });

    }
    private void buttonActions(Button button) {
        button.setOnMouseEntered(e -> animate(button, 20, "#2681ff"));
        button.setOnMouseExited(e -> animate(button, 0, "#91bfff"));
    }
    private void animate(Button button, int x, String c) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(150),
                        new KeyValue(button.translateXProperty(), x),
                        new KeyValue(button.textFillProperty(), Color.web(c))
                )
        );
        timeline.play();
    }
}
