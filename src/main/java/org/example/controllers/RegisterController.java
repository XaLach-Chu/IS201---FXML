package org.example.controllers;

import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.example.utils.ApiUtil;
import org.example.utils.SqlUtil;
import org.example.utils.Utilities;
import org.example.views.LoginView;
import org.example.views.RegisterView;

import java.net.HttpURLConnection;

public class RegisterController {
    private RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
        initialize();
    }

    private void initialize() {
        registerView.getLoginLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new LoginView().show();
            }
        });

        registerView.getRegisterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateInput()) {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "OOPS");
                    return;
                }

                String userName = registerView.getUserNameField().getText();
                String email = registerView.getEmailField().getText();
                String password = registerView.getPasswordField().getText();
                String phoneNumber = registerView.getPhoneNumberField().getText();

                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("name", userName);
                jsonData.addProperty("email", email);
                jsonData.addProperty("password", password);
                jsonData.addProperty("phoneNumber", phoneNumber);

                boolean postCreateAccountStatus = SqlUtil.postCreateUser(jsonData);
                if (postCreateAccountStatus) {
                    Utilities.showAlertDialog(Alert.AlertType.CONFIRMATION, "Account created");
                    new LoginView().show();
                } else {
                    Utilities.showAlertDialog(Alert.AlertType.ERROR, "Something went wrong :(");
                }

            }
        });
    }

    private boolean validateInput() {
        if (registerView.getEmailField().getText().isEmpty()) return false;
        if (registerView.getUserNameField().getText().isEmpty()) return false;
        if (registerView.getPasswordField().getText().isEmpty()) return false;
        if (registerView.getRePasswordField().getText().isEmpty()) return false;
        if (registerView.getPhoneNumberField().getText().isEmpty()) return false;
        if (!registerView.getRePasswordField().getText().equals(registerView.getPasswordField().getText()))
            return false;
        if (SqlUtil.getUserByEmail(registerView.getEmailField().getText())) return false;

        return true;
    }
}
