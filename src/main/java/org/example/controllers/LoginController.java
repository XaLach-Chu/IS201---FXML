package org.example.controllers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.example.utils.ApiUtil;
import org.example.utils.Utilities;
import org.example.views.LoginView;
import org.example.views.RegisterView;

import java.io.IOException;
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

                HttpURLConnection conn = null;
                try {
                    conn = ApiUtil.fetchApi(
                            "/api/v1/user/login?email=" + email + "&password=" + password,
                            ApiUtil.RequestMethod.POST, // method
                            null // json
                    );

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
