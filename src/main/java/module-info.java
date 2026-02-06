module springboot.client {
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    exports org.example;

    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    opens org.example.utils to javafx.fxml;
}