module springboot.client {
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires javafx.media;
    requires javafx.web;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    exports org.example;

    opens org.example to javafx.fxml;
    opens org.example.utils to javafx.fxml;
    opens org.example.controller to javafx.fxml;

}