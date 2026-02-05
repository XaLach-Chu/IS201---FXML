package org.example.controllers;

import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.example.utils.ApiUtil;
import org.example.utils.Utilities;
import org.example.utils.ViewNavigator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public VBox loginForm;
    public Label projectNameLabel;
    public TextField emailField;
    public PasswordField passwordField;
    public Button loginButton;
    public Label registerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Login Button
        loginButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loginButton.setStyle("-fx-background-color: #2f81f5");
            }
        });
        loginButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loginButton.setStyle("-fx-background-color: #388bff");
            }
        });

        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateInput()) {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "Missing Input(s)");
                    return;
                }

                String email = emailField.getText();
                String password = passwordField.getText();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", email);
                jsonObject.addProperty("password", password);

                HttpURLConnection conn = null;
                try {
                    conn = ApiUtil.fetchApi(
                            "/auth/login",
                            ApiUtil.RequestMethod.POST,
                            jsonObject
                    );
                    assert conn != null;
                    InputStream is = conn.getInputStream();

                    if (conn.getResponseCode() != 200) {
                        Utilities.showAlertDialog(Alert.AlertType.ERROR, "Login Failed");
                    }
                    else {
                        Utilities.showAlertDialog(Alert.AlertType.CONFIRMATION, "Login Successfully");
                    }


                } catch (IOException e) {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "Login Failed.");
                    e.printStackTrace();
                }

            }
        });



        // Register Label
        registerLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                registerLabel.setStyle("-fx-text-fill: #266bff");
            }
        });
        registerLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                registerLabel.setStyle("-fx-text-fill: black");
            }
        });

        registerLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ViewNavigator.switchView("/views/RegisterView.fxml");
            }
        });

    }

    private boolean validateInput() {
        String email = emailField.getText();
        String password = passwordField.getText();
        return !email.isBlank() && !password.isBlank();
    }


}