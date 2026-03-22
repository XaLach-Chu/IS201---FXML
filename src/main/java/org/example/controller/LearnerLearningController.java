package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class LearnerLearningController {

    @FXML
    private StackPane videoPane;

    public void initialize() {
        String path = getClass().getResource("/videos/demo.mp4").toExternalForm();

        Media media = new Media(path);
        MediaPlayer player = new MediaPlayer(media);
        MediaView view = new MediaView(player);

        view.setFitWidth(900);
        view.setPreserveRatio(true);

        videoPane.getChildren().add(view);

        player.play();
    }
}