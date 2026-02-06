package org.example.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.security.SessionManager;
import org.example.utils.ApiUtil;
import org.example.utils.Utilities;
import org.example.utils.ViewNavigator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public Label emailText;
    public Label passwordText;

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
                        return;
                    }
                    else {
                        Utilities.showAlertDialog(Alert.AlertType.CONFIRMATION, "Login Successfully");
                    }

                    JsonObject data = JsonParser
                            .parseReader(new InputStreamReader(is))
                            .getAsJsonObject();
                    SessionManager.setToken(data.get("token").getAsString());
                    SessionManager.setRole(data.get("role").getAsString());

                    ViewNavigator.switchView("/views/ProfileView.fxml", true);

//                    System.out.println(SessionManager.getToken() + " || " + SessionManager.getRole());


                } catch (IOException e) {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "Login Failed.");
//                    e.printStackTrace();
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
                ViewNavigator.switchView("/views/RegisterView.fxml", false);
            }
        });

        emailText.textFillProperty().bind(
                Bindings.when(emailField.textProperty().isEmpty())
                        .then(Color.web("#000000"))
                        .otherwise(Color.web("#388bff"))
        );
        passwordText.textFillProperty().bind(
                Bindings.when(passwordField.textProperty().isEmpty())
                        .then(Color.web("#000000"))
                        .otherwise(Color.web("#388bff"))
        );

    }

    private boolean validateInput() {
        String email = emailField.getText();
        String password = passwordField.getText();
        return !email.isBlank() && !password.isBlank();
    }


}