package org.example.controllers.usersView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.dto.requests.UserViewRequest;
import org.example.utils.Utilities;

import java.net.URL;
import java.util.ResourceBundle;

public class UserTagController implements Initializable {

    UserViewRequest user;

    public ImageView userAvatar;
    public Text userIdText;
    public Text usernameText;
    public Text emailText;
    public Text phoneNumberText;
    public Text createdAtText;
    public Text roleText;

    public Button banButton;
    public Button editButton;
    public Button deleteButton;

    public void setUser(UserViewRequest user) {
        this.user = user;
        userIdText.setText(user.getUserId());
        usernameText.setText(user.getUsername());
        emailText.setText(user.getEmail());
        phoneNumberText.setText(user.getPhoneNumber());
        createdAtText.setText(
                user.getCreatedAt().getDayOfMonth()
                + " - " + user.getCreatedAt().getMonthValue()
                + " - " + user.getCreatedAt().getYear()
        );
        roleText.setText(Utilities.roleConvert(user.getRole()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public ImageView getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(ImageView userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Text getUserIdText() {
        return userIdText;
    }

    public void setUserIdText(Text userIdText) {
        this.userIdText = userIdText;
    }

    public Text getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(Text usernameText) {
        this.usernameText = usernameText;
    }

    public Text getEmailText() {
        return emailText;
    }

    public void setEmailText(Text emailText) {
        this.emailText = emailText;
    }

    public Text getPhoneNumberText() {
        return phoneNumberText;
    }

    public void setPhoneNumberText(Text phoneNumberText) {
        this.phoneNumberText = phoneNumberText;
    }

    public Text getCreatedAtText() {
        return createdAtText;
    }

    public void setCreatedAtText(Text createdAtText) {
        this.createdAtText = createdAtText;
    }

    public Text getRoleText() {
        return roleText;
    }

    public void setRoleText(Text roleText) {
        this.roleText = roleText;
    }

    public Button getBanButton() {
        return banButton;
    }

    public void setBanButton(Button banButton) {
        this.banButton = banButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

}
