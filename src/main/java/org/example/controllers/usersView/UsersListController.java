package org.example.controllers.usersView;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.dto.requests.UserViewRequest;
import org.example.utils.ApiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersListController implements Initializable {

    public VBox usersContainer;
    public TextField searchBar;
    public FontAwesomeIconView searchIcon;
    public CheckBox learnerFilter;
    public CheckBox mentorFilter;
    public CheckBox supportFilter;
    public CheckBox financeFilter;
    public CheckBox qaFilter;
    public CheckBox adminFilter;
    public CheckBox eUserFilter;
    public CheckBox iUserFilter;
    public Button searchButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<UserViewRequest> users = ApiUtil.fetchUsers("/api/admin/users", ApiUtil.RequestMethod.GET, null);
        for (UserViewRequest user : users) {
            addUserToUI(user);
        }

        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                usersContainer.getChildren().clear();
                List<UserViewRequest> users = ApiUtil.fetchUsers("/api/admin/users", ApiUtil.RequestMethod.GET, null);
                for (UserViewRequest user : users) {
                    addUserToUI(user);
                }
            }
        });

    }

    private void addUserToUI(UserViewRequest user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsersViewing/UserTag.fxml"));
            HBox userNode = loader.load();
            UserTagController controller = loader.getController();
            controller.setUser(user);
            usersContainer.getChildren().add(userNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
