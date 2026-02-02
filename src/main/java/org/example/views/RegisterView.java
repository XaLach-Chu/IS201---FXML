package org.example.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.controllers.RegisterController;
import org.example.utils.Utilities;
import org.example.utils.ViewNavigator;

import java.util.Objects;

public class RegisterView {
    private Label signupLabel = new Label("Sign Up");
    private TextField emailField = new TextField();
    private TextField userNameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private PasswordField rePasswordField = new PasswordField();
    private TextField phoneNumberField = new TextField();
    private Button registerButton = new Button("Register");
    private Label loginLabel = new Label("Already have an account? Login!");

    public void show() {
        Scene scene = createScene();

        new RegisterController(this);
        ViewNavigator.switchView(scene);
    }

    private Scene createScene() {

        VBox mainContainerBox = new VBox(20);

        VBox registerFormBox = createRegisterForm();

        mainContainerBox.getChildren().addAll(signupLabel, registerFormBox);
        mainContainerBox.setAlignment(Pos.CENTER);

        return new Scene(mainContainerBox, Utilities.WIDTH, Utilities.HEIGHT);
    }

    private VBox createRegisterForm() {
        VBox registerForm = new VBox(5);
        registerForm.getChildren().addAll(
                userNameField,
                emailField,
                passwordField,
                rePasswordField,
                phoneNumberField,
                registerButton,
                loginLabel
        );

        // CSS class name

        // WH
        registerForm.setMaxWidth(600);


        registerForm.setAlignment(Pos.CENTER);
        return registerForm;
    }

    public Label getSignupLabel() {
        return signupLabel;
    }

    public void setSignupLabel(Label signupLabel) {
        this.signupLabel = signupLabel;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public TextField getUserNameField() {
        return userNameField;
    }

    public void setUserNameField(TextField userNameField) {
        this.userNameField = userNameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public PasswordField getRePasswordField() {
        return rePasswordField;
    }

    public void setRePasswordField(PasswordField rePasswordField) {
        this.rePasswordField = rePasswordField;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(TextField phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }

    public Label getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(Label loginLabel) {
        this.loginLabel = loginLabel;
    }
}
