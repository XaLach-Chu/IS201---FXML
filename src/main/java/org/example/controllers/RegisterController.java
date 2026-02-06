package org.example.controllers;

import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.utils.SqlUtil;
import org.example.utils.Utilities;
import org.example.utils.ViewNavigator;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public Label registerLabel;
    public TextField usernameField;
    public TextField emailField;
    public PasswordField passwordField;
    public PasswordField rePasswordField;
    public TextField phoneNumberField;
    public Button registerButton;
    public Label loginLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // REGISTER BUTTON
        // CSS
        registerButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                registerButton.setStyle("-fx-background-color: #2f81f5");
            }
        });
        registerButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                registerButton.setStyle("-fx-background-color: #388bff");
            }
        });
        // CODE
        registerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateInput()) {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "Missing Input(s)");
                    return;
                }

                String username = usernameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String phoneNumber = phoneNumberField.getText();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", username);
                jsonObject.addProperty("email", email);
                jsonObject.addProperty("password", password);
                jsonObject.addProperty("phoneNumber", phoneNumber);

                boolean status = SqlUtil.postCreateUser(jsonObject);
                if (status) {
                    Utilities.showAlertDialog(Alert.AlertType.CONFIRMATION, "Account Created!");
                    ViewNavigator.switchView("/views/LoginView.fxml", false);
                } else {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "Something went wrong :(");
                }
            }

        });

        // LOGIN LABEL
        // CSS

        loginLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loginLabel.setStyle("-fx-text-fill: #266bff");
            }
        });
        loginLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loginLabel.setStyle("-fx-text-fill: black");
            }
        });

        // CODE
        loginLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ViewNavigator.switchView("/views/LoginView.fxml", false);
            }
        });

    }

    private boolean validateInput() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String rePassword = rePasswordField.getText();

        if (!password.equals(rePassword)) return false;
        return (!username.isBlank() && !password.isBlank() && !email.isBlank());
    }

}
