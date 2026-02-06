package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.utils.ViewNavigator;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ViewNavigator.setMainStage(stage);
        ViewNavigator.switchView("/views/LoginView.fxml", false);
        stage.setTitle("DCMM UIT");
//        stage.setMaximized(true);
        stage.show();

    }

}