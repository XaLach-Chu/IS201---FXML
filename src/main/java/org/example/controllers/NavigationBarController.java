package org.example.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBarController implements Initializable {

    public FontAwesomeIconView homeIcon;
    public FontAwesomeIconView courseIcon;
    public FontAwesomeIconView cartIcon;
    public FontAwesomeIconView supportIcon;
    public FontAwesomeIconView chatIcon;
    public FontAwesomeIconView profileIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iconAnimation(homeIcon, 25, 35);
        iconAnimation(courseIcon, 22, 32);
        iconAnimation(cartIcon, 20, 30);
        iconAnimation(supportIcon, 22, 33);
        iconAnimation(chatIcon, 22, 32);
        iconAnimation(profileIcon, 23, 30);
    }

    private void iconAnimation(FontAwesomeIconView icon, int def, int to) {
        icon.setOnMouseEntered(e -> animateSize(icon, to));
        icon.setOnMouseExited(e -> animateSize(icon, def));
    }

    private void animateSize(FontAwesomeIconView icon, int to) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(icon.glyphSizeProperty(), to);
        KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

}
