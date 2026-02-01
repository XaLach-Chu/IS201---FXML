package org.example.views;


import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.utils.Utilities;
import org.example.utils.ViewNavigator;

import javax.swing.text.View;
import java.util.Objects;

public class LoginView {
    private Label projectNameLabel = new Label("EduPath");
    private TextField userNameField = new TextField();
    private PasswordField userPasswordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private Label signupLabel = new Label("Register");

    public void show() {
        Scene scene = createScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/LoginView.css")).toExternalForm());
        ViewNavigator.switchView(scene);
    }

    private Scene createScene() {
        VBox mainContainerBox = new VBox();
        mainContainerBox.getStyleClass().addAll("main-background");

        VBox loginFormBox = createLoginFormBox();
        loginFormBox.getStyleClass().addAll("login-container");

        mainContainerBox.getChildren().addAll(projectNameLabel, loginFormBox);
        mainContainerBox.setAlignment(Pos.CENTER);

        return new Scene(mainContainerBox, Utilities.WIDTH, Utilities.HEIGHT);
    }
    private VBox createLoginFormBox() {
        VBox loginFormBox = new VBox(5);
        loginFormBox.getChildren().addAll(userNameField, userPasswordField, loginButton, signupLabel);

        // CSS class name
        projectNameLabel.getStyleClass().addAll("header");
        userNameField.getStyleClass().addAll("field", "user-name", "border");
        userPasswordField.getStyleClass().addAll("field", "user-password", "border");
        loginButton.getStyleClass().addAll("login-button", "border");
        signupLabel.getStyleClass().addAll("register-link");

        // setup
        userNameField.setPromptText("Username");
        userPasswordField.setPromptText("Password");

        VBox.setMargin(loginButton, new Insets(20, 0, 0, 0));
        loginButton.setMinWidth(200);
        VBox.setMargin(signupLabel, new Insets(10, 0, 0, 0));
        VBox.setMargin(userNameField, new Insets(20, 0, 0, 0));

        // WH
        userNameField.setMaxWidth(600);
        userPasswordField.setMaxWidth(600);

        // animation
        loginButton.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), loginButton);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();
        });

        loginButton.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), loginButton);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        loginFormBox.setAlignment(Pos.CENTER);
        return loginFormBox;
    }
}
