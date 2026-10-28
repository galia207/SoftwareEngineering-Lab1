package org.openjfx.lab2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.openjfx.lab2.model.User;

/**
 * Controller of the welcome screen (welcome.fxml), shown after a
 * successful login.
 */
public class WelcomeController {

    /** The main welcome message. */
    @FXML
    private Label welcomeText;

    /** Shows which user is logged in. */
    @FXML
    private Label userText;

    /**
     * Fills the screen with the details of the user who logged in.
     *
     * @param user the user that passed the login check
     */
    public void setUser(User user) {
        welcomeText.setText("Welcome!");
        userText.setText("You are logged in to GCM as " + user.getName());
    }
}
