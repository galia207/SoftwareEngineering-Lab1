/**
 * Module descriptor for the GCM login application (Lab 2).
 *
 * JavaFX loads the controllers by reflection when it reads the FXML files,
 * so the controller package must be opened to javafx.fxml.
 */
module org.openjfx.lab2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.lab2.controller to javafx.fxml;
    exports org.openjfx.lab2;
    exports org.openjfx.lab2.model;
    exports org.openjfx.lab2.controller;
}
