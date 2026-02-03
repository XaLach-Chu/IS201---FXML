package org.example.controllers;

import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.example.utils.ApiUtil;
import org.example.utils.Utilities;
import org.example.views.LoginView;
import org.example.views.RegisterView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }
    private void initialize() {
        loginView.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateUser()) {
                    System.out.println("Missing inputs");// missing inputs
                    return;
                }

                String email = loginView.getUserNameField().getText();
                String password = loginView.getUserPasswordField().getText();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", email);
                jsonObject.addProperty("password", password);

                HttpURLConnection conn = null;
                try {
                    conn = ApiUtil.fetchApi(
                            "/auth/login",
                            ApiUtil.RequestMethod.POST, // method
                            jsonObject // json
                    );

                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    String responseJson = sb.toString();
                    System.out.println(responseJson);


                    assert conn != null;
                    if (conn.getResponseCode() != 200) {
                        Utilities.showAlertDialog(Alert.AlertType.ERROR, "Login Failed");
                    }
                    else {
                        Utilities.showAlertDialog(Alert.AlertType.CONFIRMATION, "Login Successfully");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        loginView.getRegisterLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new RegisterView().show();
            }
        });
    }

    private boolean validateUser() {
        String email = loginView.getUserNameField().getText();
        String password = loginView.getUserPasswordField().getText();

        if (email.isBlank() || password.isBlank())
            return false;
        else
            return true;
    }
}
